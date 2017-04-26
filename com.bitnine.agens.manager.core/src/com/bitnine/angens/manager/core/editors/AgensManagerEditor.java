package com.bitnine.angens.manager.core.editors;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.bitnine.agens.manager.engine.core.AgensManagerSQLImpl;
import com.bitnine.agens.manager.engine.core.dao.domain.Instance;
import com.bitnine.angens.manager.core.editors.parts.activities.AnalyzeStatisticsComposite;
import com.bitnine.angens.manager.core.editors.parts.activities.BasicStatistics_AverageComposite;
import com.bitnine.angens.manager.core.editors.parts.activities.CheckpointActivityComposite;
import com.bitnine.angens.manager.core.editors.parts.activities.CurrentReplicationStatusComposite;
import com.bitnine.angens.manager.core.editors.parts.activities.IOStatistics_AverageComposite;
import com.bitnine.angens.manager.core.editors.parts.activities.ReplicationDelaysComposite;
import com.bitnine.angens.manager.core.editors.parts.activities.VacuumCancelsComposite;
import com.bitnine.angens.manager.core.editors.parts.information.IndexComposite;
import com.bitnine.angens.manager.core.editors.parts.information.ParametersComposite;
import com.bitnine.angens.manager.core.editors.parts.information.TableComposite;
import com.bitnine.angens.manager.core.editors.parts.lableprovider.AgensMAPLabelProvider;
import com.bitnine.angens.manager.core.editors.parts.lableprovider.AlertMessageLabelProvider;
import com.bitnine.angens.manager.core.editors.parts.logs.LogComposite;
import com.bitnine.angens.manager.core.editors.parts.os.CPUUsageTableComposite;
import com.bitnine.angens.manager.core.editors.parts.os.DiskUsagePerTableSpaceTableComposite;
import com.bitnine.angens.manager.core.editors.parts.os.DiskUsageperTableComposite;
import com.bitnine.angens.manager.core.editors.parts.os.IoSizeTableComposite;
import com.bitnine.angens.manager.core.editors.parts.os.IoTimeTableComposite;
import com.bitnine.angens.manager.core.editors.parts.os.IoUsageTableComposite;
import com.bitnine.angens.manager.core.editors.parts.os.LoadAverageTableComposite;
import com.bitnine.angens.manager.core.editors.parts.sql.FragmentedTableComposite;
import com.bitnine.angens.manager.core.editors.parts.sql.FunctionsComposite;
import com.bitnine.angens.manager.core.editors.parts.sql.HeavilyAccessedTableComposite;
import com.bitnine.angens.manager.core.editors.parts.sql.HeavilyUpdatedTableComposite;
import com.bitnine.angens.manager.core.editors.parts.sql.LockConflictsComposite;
import com.bitnine.angens.manager.core.editors.parts.sql.LongTransactionsComposite;
import com.bitnine.angens.manager.core.editors.parts.sql.LowDensityTableComposite;
import com.bitnine.angens.manager.core.editors.parts.sql.StatementsComposite;
import com.bitnine.angens.manager.core.editors.parts.statistics.DatabaseSizeTableComposite;
import com.bitnine.angens.manager.core.editors.parts.statistics.DatabaseStatisticsTableComposite;
import com.bitnine.angens.manager.core.editors.parts.statistics.DiskReadComposite;
import com.bitnine.angens.manager.core.editors.parts.statistics.InstanceProcessesRatioTableComposite;
import com.bitnine.angens.manager.core.editors.parts.statistics.InstanceProcessesTableComposite;
import com.bitnine.angens.manager.core.editors.parts.statistics.MemoryUsageComposite;
import com.bitnine.angens.manager.core.editors.parts.statistics.RecoveryConflictsTableComposite;
import com.bitnine.angens.manager.core.editors.parts.statistics.TableSizeComposite;
import com.bitnine.angens.manager.core.editors.parts.statistics.TransactionStatisticsTableComposite;
import com.bitnine.angens.manager.core.editors.parts.statistics.WALStatisticsStatsTableComposite;
import com.bitnine.angens.manager.core.editors.parts.statistics.WALStatisticsTableComposite;
import com.bitnine.angens.manager.core.editors.parts.summary.AlertMessageComposite;
import com.bitnine.angens.manager.core.editors.parts.summary.SummaryComposite;
import com.bitnine.angens.manager.core.utils.AgensChartUtils;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * agens manager editor
 * 
 * @author hangum
 *
 */
public class AgensManagerEditor extends EditorPart {
	private static final Logger logger = Logger.getLogger(AgensManagerEditor.class);
	public static final String ID = "com.bitnine.angens.manager.core.editor.main";

	/** 모니터링이 가능한지 유무 */
	protected boolean isMonitoringStart = false;
	/** 모니터링 대상 DB */
	private UserDBDAO userDB;

	/** 모니터링 인스턴스 combo */
	private Combo comboInstance;
	/** 모니터링 주기 */
	private Combo comboMonitoringTerm;
	
	private ScrolledComposite scrolledComposite;
	private Composite compositeBody;
	
	// sub composite
	private Group compSummary;
	private Group compositeOS;
	private Group compositeStatics;
	private Group compositeSQL;
	private Group compositeActivities;
	private Group compositeInformation;
	private Group compositeLog;

	/**
	 * 
	 */
	public AgensManagerEditor() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(3, false));

		Group grpConfiguration = new Group(parent, SWT.NONE);
		grpConfiguration.setLayout(new GridLayout(2, false));
		grpConfiguration.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		grpConfiguration.setText("Configuration");

		Label lblInstance = new Label(grpConfiguration, SWT.NONE);
		lblInstance.setText("Instance");

		comboInstance = new Combo(grpConfiguration, SWT.READ_ONLY);
		GridData gd_comboInstance = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_comboInstance.widthHint = 100;
		comboInstance.setLayoutData(gd_comboInstance);
		
		Label lblMonitoringTerm = new Label(grpConfiguration, SWT.NONE);
		lblMonitoringTerm.setText("Monitoring term\n(minute)");

		comboMonitoringTerm = new Combo(grpConfiguration, SWT.READ_ONLY);
		comboMonitoringTerm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AgensChartUtils.setMonitoringTerm(comboMonitoringTerm.getText());
			}
		});
		GridData gd_comboTerm = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_comboTerm.widthHint = 100;
		comboMonitoringTerm.setLayoutData(gd_comboTerm);
		
		comboMonitoringTerm.add("1");
		comboMonitoringTerm.add("3");
		comboMonitoringTerm.add("5");
		comboMonitoringTerm.add("10");
		comboMonitoringTerm.add("20");
		comboMonitoringTerm.add("30");
		comboMonitoringTerm.add("60");
		
		initUI();
		if (!isMonitoringStart) return;
		
		// crate ui
		Group grpDashboard = new Group(parent, SWT.NONE);
		grpDashboard.setLayout(new GridLayout(1, false));
		grpDashboard.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpDashboard.setText("Dashboard");
		
		Composite compositeHead = new Composite(grpDashboard, SWT.NONE);
		compositeHead.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeHead.setLayout(new GridLayout(1, false));
		
		ToolBar toolBar = new ToolBar(compositeHead, SWT.FLAT | SWT.RIGHT);
		ToolItem tltmSummary = new ToolItem(toolBar, SWT.NONE);
		tltmSummary.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scrolledComposite.setOrigin(compSummary.getLocation());
			}
		});
		tltmSummary.setText("Summary");
		
		ToolItem tltmOS = new ToolItem(toolBar, SWT.NONE);
		tltmOS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scrolledComposite.setOrigin(compositeOS.getLocation());
			}
		});
		tltmOS.setText("OS");
		
		ToolItem tltmStatistics = new ToolItem(toolBar, SWT.NONE);
		tltmStatistics.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scrolledComposite.setOrigin(compositeStatics.getLocation());
			}
		});
		tltmStatistics.setText("Statistics");
		
		ToolItem tltmSQL = new ToolItem(toolBar, SWT.NONE);
		tltmSQL.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scrolledComposite.setOrigin(compositeSQL.getLocation());
			}
		});
		tltmSQL.setText("SQL");
		
		ToolItem tltmActivities = new ToolItem(toolBar, SWT.NONE);
		tltmActivities.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scrolledComposite.setOrigin(compositeActivities.getLocation());
			}
		});
		tltmActivities.setText("Activities");
		
		ToolItem tltmInformation = new ToolItem(toolBar, SWT.NONE);
		tltmInformation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scrolledComposite.setOrigin(compositeInformation.getLocation());
			}
		});
		tltmInformation.setText("Information");
		
		ToolItem tltmLog = new ToolItem(toolBar, SWT.NONE);
		tltmLog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				scrolledComposite.setOrigin(compositeLog.getLocation());
			}
		});
		tltmLog.setText("Log");
		
		scrolledComposite = new ScrolledComposite(grpDashboard, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(scrolledComposite);
		
		compositeBody = new Composite(scrolledComposite, SWT.NONE);
		compositeBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeBody.setLayout(new GridLayout(1, true));
	
		createSummary();
		createOS();
		createStatistics();
		createSQL();
		createActivities();
		createInformation();
	    createLog();
		
		// initialize scrolled composite
		scrolledComposite.setContent(compositeBody);
		new Label(parent, SWT.NONE);
		scrolledComposite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle r = compositeBody.getClientArea();
				scrolledComposite.setMinSize(compositeBody.computeSize(r.width, SWT.DEFAULT));
			}
		});
	}
	
	/**
	 * Initialize user interface
	 */
	private void initUI() {
		try {
			List<Instance> listInstance = AgensManagerSQLImpl.getInstanceInfo(userDB);

			for (Instance instance : listInstance) {
				String strName = instance.getHostname() + ":" + instance.getPort();
				comboInstance.add(strName);
				comboInstance.setData(strName, instance);
			}
			comboInstance.select(0);
			
			// setting monitoring term
			comboMonitoringTerm.setText(AgensChartUtils.getMonitoringTerm());

			isMonitoringStart = true;
		} catch (Exception e) {
			logger.error("get instance", e);

			MessageDialog.openError(null, "Error", "모니터링 테이블이 발견되지 않았습니다. 확인하여 주십시오.");
		}
	}

	/**
	 * get instace
	 * 
	 * @return
	 */
	private Instance getInstance() {
		Object obj = comboInstance.getData(comboInstance.getText());
		if (obj != null) return (Instance) obj;
		return null;
	}

	/**
	 * create summary
	 */
	private void createSummary() {
		compSummary = new Group(compositeBody, SWT.NONE);
		compSummary.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compSummary.setLayout(new GridLayout(1, false));
		compSummary.setText("Summary");

		SummaryComposite summaryComposite = new SummaryComposite(compSummary, userDB, getInstance());
		summaryComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		AlertMessageComposite alertComposite = new AlertMessageComposite(compSummary, userDB, getInstance(), new AlertMessageLabelProvider());
		alertComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	/**
	 * create statistics
	 */
	private void createStatistics() {			
		compositeStatics = new Group(compositeBody, SWT.NONE);
		compositeStatics.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeStatics.setLayout(new GridLayout(1, false));
		compositeStatics.setText("Statics");
		
		// database statistics table
		DatabaseStatisticsTableComposite databaseComposite = new DatabaseStatisticsTableComposite(compositeStatics, userDB, getInstance(), new AgensMAPLabelProvider());
		databaseComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		// transaction statistics table 
		TransactionStatisticsTableComposite transactionComposite = new TransactionStatisticsTableComposite(compositeStatics, userDB, getInstance());
		transactionComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		// database size table 
		DatabaseSizeTableComposite databaseSizeComposite = new DatabaseSizeTableComposite(compositeStatics, userDB, getInstance());
		databaseSizeComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		// recovery conflicts
		RecoveryConflictsTableComposite recoveryConflictsComposite = new RecoveryConflictsTableComposite(compositeStatics, userDB, getInstance(), new AgensMAPLabelProvider());
		recoveryConflictsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		// instance activity
		// WAL statistic table
		WALStatisticsStatsTableComposite walStatsComposite = new WALStatisticsStatsTableComposite(compositeStatics, userDB, getInstance(), new AgensMAPLabelProvider());
		walStatsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			
		// WAL statistic table
		WALStatisticsTableComposite walConflictsComposite = new WALStatisticsTableComposite(compositeStatics, userDB, getInstance());
		walConflictsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		// instance process ratio
		InstanceProcessesRatioTableComposite instanceProcessRatioComposite = new InstanceProcessesRatioTableComposite(compositeStatics, userDB, getInstance(), new AgensMAPLabelProvider());
		instanceProcessRatioComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		// instance process
		InstanceProcessesTableComposite instanceProcessComposite = new InstanceProcessesTableComposite(compositeStatics, userDB, getInstance());
		instanceProcessComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	/**
	 * create OS
	 */
	
	private void createOS() {
		compositeOS = new Group(compositeBody, SWT.NONE);
		compositeOS.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeOS.setLayout(new GridLayout(1, false));
		compositeOS.setText("OS");

		CPUUsageTableComposite cpuUsageTableComposite = new CPUUsageTableComposite(compositeOS, userDB, getInstance());
		cpuUsageTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		LoadAverageTableComposite loadAverageTableComposite = new LoadAverageTableComposite(compositeOS, userDB, getInstance());
		loadAverageTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		IoUsageTableComposite ioUsageTableComposite = new IoUsageTableComposite(compositeOS, userDB, getInstance(), new AgensMAPLabelProvider());
		ioUsageTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		IoSizeTableComposite ioSizeTableComposite = new IoSizeTableComposite(compositeOS, userDB, getInstance());
		ioSizeTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		IoTimeTableComposite ioTimeTableComposite = new IoTimeTableComposite(compositeOS, userDB, getInstance());
		ioTimeTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		MemoryUsageComposite memoryTableComposite = new MemoryUsageComposite(compositeOS, userDB, getInstance());
		memoryTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		DiskUsagePerTableSpaceTableComposite diskUseageperTablespaceTableComposite = new DiskUsagePerTableSpaceTableComposite(compositeOS, userDB, getInstance(), new AgensMAPLabelProvider());
		diskUseageperTablespaceTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		DiskUsageperTableComposite diskUseageperTableComposite = new DiskUsageperTableComposite(compositeOS, userDB, getInstance(), new AgensMAPLabelProvider());
		diskUseageperTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		 
		 // Table Size
		TableSizeComposite tableSizeComposite = new TableSizeComposite(compositeOS, userDB, getInstance());
		tableSizeComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		 
		 // Disk Read
		DiskReadComposite diskReadComposite = new DiskReadComposite(compositeOS, userDB, getInstance());
		diskReadComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	/**
	 * create sql composite
	 */
	private void createSQL() {
		compositeSQL = new Group(compositeBody, SWT.NONE);
		compositeSQL.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeSQL.setLayout(new GridLayout(1, false));
		compositeSQL.setText("SQL");

		HeavilyUpdatedTableComposite heavilyUpdatedTableComposite = new HeavilyUpdatedTableComposite(compositeSQL, userDB, getInstance(), new AgensMAPLabelProvider());
		heavilyUpdatedTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		HeavilyAccessedTableComposite heavilyAccessedTableComposite = new HeavilyAccessedTableComposite(compositeSQL, userDB, getInstance(), new AgensMAPLabelProvider());
		heavilyAccessedTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		LowDensityTableComposite lowDensityTableComposite = new LowDensityTableComposite(compositeSQL, userDB, getInstance(), new AgensMAPLabelProvider());
		lowDensityTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		FragmentedTableComposite fragmentTableTableComposite = new FragmentedTableComposite(compositeSQL, userDB, getInstance(), new AgensMAPLabelProvider());
		fragmentTableTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		FunctionsComposite functionTableComposite = new FunctionsComposite(compositeSQL, userDB, getInstance(), new AgensMAPLabelProvider());
		functionTableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		StatementsComposite statementsComposite = new StatementsComposite(compositeSQL, userDB, getInstance(), new AgensMAPLabelProvider());
		statementsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		LongTransactionsComposite longTransaction = new LongTransactionsComposite(compositeSQL, userDB, getInstance(), new AgensMAPLabelProvider());
		longTransaction.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		LockConflictsComposite lockConflicts = new LockConflictsComposite(compositeSQL, userDB, getInstance(), new AgensMAPLabelProvider());
		lockConflicts.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	/**
	 * create activities composite
	 */
	private void createActivities() {
		compositeActivities = new Group(compositeBody, SWT.NONE);
		compositeActivities.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeActivities.setLayout(new GridLayout(1, false));
		compositeActivities.setText("Activities");

		// activities composite start
		CheckpointActivityComposite checkpointActivityComposite = new CheckpointActivityComposite(compositeActivities, userDB, getInstance(), new AgensMAPLabelProvider());
		checkpointActivityComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		BasicStatistics_AverageComposite basicStatisticsComposite = new BasicStatistics_AverageComposite(compositeActivities, userDB, getInstance(), new AgensMAPLabelProvider());
		basicStatisticsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		VacuumCancelsComposite vaccumCancelsComposite = new VacuumCancelsComposite(compositeActivities, userDB, getInstance(), new AgensMAPLabelProvider());
		vaccumCancelsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		IOStatistics_AverageComposite ioStatistcsAverageComposite = new IOStatistics_AverageComposite(compositeActivities, userDB, getInstance(), new AgensMAPLabelProvider());
		ioStatistcsAverageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		AnalyzeStatisticsComposite analyzeStatisticsComposite = new AnalyzeStatisticsComposite(compositeActivities, userDB, getInstance(), new AgensMAPLabelProvider());
		analyzeStatisticsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		CurrentReplicationStatusComposite currentReplicationComposite = new CurrentReplicationStatusComposite(compositeActivities, userDB, getInstance(), new AgensMAPLabelProvider());
		currentReplicationComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		ReplicationDelaysComposite replicationDelaysComposite = new ReplicationDelaysComposite(compositeActivities, userDB, getInstance());
		replicationDelaysComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	/**
	 * create information composite
	 * 
	 */
	private void createInformation() {
		compositeInformation = new Group(compositeBody, SWT.NONE);
		compositeInformation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeInformation.setLayout(new GridLayout(1, false));
		compositeInformation.setText("Information");

		// information composite
		TableComposite tableComposite = new TableComposite(compositeInformation, userDB, getInstance(), new AgensMAPLabelProvider());
		tableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		IndexComposite indexComposite = new IndexComposite(compositeInformation, userDB, getInstance(), new AgensMAPLabelProvider());
		indexComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		ParametersComposite parameterComposite = new ParametersComposite(compositeInformation, userDB, getInstance(), new AgensMAPLabelProvider());
		parameterComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}
	
	/**
	 * create log composite
	 * 
	 */
	private void createLog() {
		compositeLog = new Group(compositeBody, SWT.NONE);
		compositeLog.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeLog.setLayout(new GridLayout(1, false));
		compositeLog.setText("Log");

		// log composite
		LogComposite logComposite = new LogComposite(compositeLog, userDB, getInstance());
		logComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public void setFocus() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		AgensManagerEditorInput qei = (AgensManagerEditorInput) input;
		userDB = qei.getUserDB();

		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
}
