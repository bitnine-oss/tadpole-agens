package com.bitnine.angens.manager.core.editors.parts.logs;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import com.bitnine.agens.manager.engine.core.dao.domain.Instance;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;
import com.hangum.tadpole.engine.sql.util.QueryUtils;
import com.hangum.tadpole.engine.sql.util.resultset.QueryExecuteResultDTO;
import com.hangum.tadpole.engine.sql.util.resultset.TadpoleResultSet;
import com.hangum.tadpole.engine.sql.util.tables.SQLResultLabelProvider;
import com.hangum.tadpole.engine.sql.util.tables.SQLResultSorter;
import com.hangum.tadpole.engine.sql.util.tables.TableUtil;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

/**
 * Log composite
 * 
 * @author hangum
 *
 */
public class LogComposite extends Composite {
	private static final Logger logger = Logger.getLogger(LogComposite.class);	
	private String BASE_QUERY = "SELECT to_char(timestamp, 'YYYY-MM-DD HH24:MI:SS.MS') AS timestamp, username, database, pid, client_addr, session_id, session_line_num, ps_display, " +  
			" to_char(session_start, 'YYYY-MM-DD HH24:MI:SS.MS') AS session_start, vxid, xid, " +  
			" elevel, sqlstate, message, detail, hint, query, query_pos, context, user_query, user_query_pos, location, application_name " +  
			" FROM statsrepo.log " + 
			" WHERE instid = #snapid# ";  
//			AND  elevel = #_LEVEL#
//			AND  username = #_USERNAME#
//			AND  database = #_DATABASE#
//			AND  message = #_MESSAGE#"";

	private UserDBDAO userDB;
	private Instance instance;
	
	private Combo comboLevel;
	private Text textUserName;
	private Text textDatabase;
	private Text textMessage;
	
	private TableViewer tableViewer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LogComposite(Composite parent, UserDBDAO userDB, Instance instance) {
		super(parent, SWT.NONE);
		setLayout(new GridLayout(1, false));
		
		// initialize user composite data
		this.userDB = userDB;
		this.instance = instance;
		
		Group grpSearchOption = new Group(this, SWT.NONE);
		grpSearchOption.setLayout(new GridLayout(6, false));
		grpSearchOption.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpSearchOption.setText("Search Option");
		
		Label lblLevel = new Label(grpSearchOption, SWT.NONE);
		lblLevel.setText("Level");
		
		comboLevel = new Combo(grpSearchOption, SWT.READ_ONLY);
		comboLevel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search();
			}
		});
		comboLevel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboLevel.setVisibleItemCount(9);
		
		comboLevel.add("");
		comboLevel.add("DEBUG");
		comboLevel.add("INFO");
		comboLevel.add("NOTICE");
		comboLevel.add("WARNING");
		comboLevel.add("ERROR");
		comboLevel.add("LOG");
		comboLevel.add("FATAL");
		comboLevel.add("PANIC");
		comboLevel.select(7);
		
		Label lblUserName = new Label(grpSearchOption, SWT.NONE);
		lblUserName.setText("User Name");
		
		textUserName = new Text(grpSearchOption, SWT.BORDER);
		textUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == SWT.Selection) {
					search();
				}
			}
		});
		textUserName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDatabase = new Label(grpSearchOption, SWT.NONE);
		lblDatabase.setText("DataBase");
		
		textDatabase = new Text(grpSearchOption, SWT.BORDER);
		textDatabase.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == SWT.Selection) {
					search();
				}
			}
		});
		textDatabase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblMessage = new Label(grpSearchOption, SWT.NONE);
		lblMessage.setText("Message");
		
		textMessage = new Text(grpSearchOption, SWT.BORDER);
		textMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == SWT.Selection) {
					search();
				}
			}
		});
		textMessage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		
		Button btnSearch = new Button(grpSearchOption, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search();
			}
		});
		btnSearch.setText("Search");
		

		Composite compositeBody = new Composite(this, SWT.NONE);
		compositeBody.setLayout(new GridLayout(1, false));
		compositeBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		tableViewer = new TableViewer(compositeBody, SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		initUI();
		search();
	}
	
	/**
	 * initialize UI
	 */
	private void initUI() {
		BASE_QUERY = StringUtils.replace(BASE_QUERY, "#snapid#", ""+instance.getInstid());
	}
	
	/**
	 * search
	 */
	private void search() {
		
		String strFullyQuery = BASE_QUERY;
		String strLevel = StringUtils.trimToEmpty(comboLevel.getText());
		String strUserName = StringUtils.trimToEmpty(textUserName.getText());
		String strDatabase = StringUtils.trimToEmpty(textDatabase.getText());
		String strMessage = StringUtils.trimToEmpty(textMessage.getText());

		if(!strLevel.equals("")) {
			strFullyQuery += String.format(" AND elevel = '%s' ", strLevel);
		}
		if(!strUserName.equals("")) {
			strFullyQuery += String.format(" AND username = '%s' ", strUserName);
		}
		if(!strDatabase.equals("")) {
			strFullyQuery += String.format(" AND database = '%s' ", strDatabase);
		}
		if(!strMessage.equals("")) {
			strFullyQuery += " AND message like '%" + strMessage + "%' ";
		}
		strFullyQuery += " ORDER BY timestamp DESC";
		
		QueryExecuteResultDTO showStatus = new QueryExecuteResultDTO();
		try {
			showStatus = QueryUtils.executeQuery(userDB, strFullyQuery, 0, 500);
		} catch (Exception e) {
			logger.error("search exception", e);
		}
		
		// table data를 생성한다.
		final TadpoleResultSet trs = showStatus.getDataList();
		final SQLResultSorter sqlSorter = new SQLResultSorter(-999);
		
		SQLResultLabelProvider.createTableColumn(tableViewer, showStatus, sqlSorter);
		
		tableViewer.setLabelProvider(new SQLResultLabelProvider(true, showStatus, ""));
		tableViewer.setContentProvider(new ArrayContentProvider());
		
		// 쿼리를 설정한 사용자가 설정 한 만큼 보여준다.
		tableViewer.setInput(trs.getData());
		tableViewer.setSorter(sqlSorter);
	
		// Pack the columns
		TableUtil.packTable(tableViewer.getTable());
	}

	@Override
	protected void checkSubclass() {
	}
}
