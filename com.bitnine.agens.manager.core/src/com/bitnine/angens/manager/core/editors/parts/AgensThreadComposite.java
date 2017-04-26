package com.bitnine.angens.manager.core.editors.parts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.rap.rwt.service.ServerPushSession;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.bitnine.agens.manager.engine.core.AgensManagerSQLImpl;
import com.bitnine.agens.manager.engine.core.dao.domain.Instance;
import com.bitnine.angens.manager.core.utils.AgensGraphDefine;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;
import com.hangum.tadpole.session.manager.SessionManager;

/**
 * agens thread composite
 * 
 * @author hangum
 *
 */
public abstract class AgensThreadComposite extends Composite {
	private static final Logger logger = Logger.getLogger(AgensThreadComposite.class);
	
	/** server push session */
	final ServerPushSession spsInstance = new ServerPushSession();
	protected boolean isUIThreadRunning = false;
	
	protected UserDBDAO userDB;
	protected Instance instance;

	public AgensThreadComposite(Composite parent,  UserDBDAO userDB, Instance instance) {
		super(parent, SWT.NONE);
		
		this.userDB = userDB;
		this.instance = instance;
		
		// create default composite
		
		// start monitoring
		startInstanceMon();
	}

	/**
	 * start instance monitoring
	 */
	protected void startInstanceMon() {
		if(!isUIThreadRunning) {
			spsInstance.start();
			Thread bgThread = new Thread( startUIThread() );
			bgThread.setDaemon( true );
			bgThread.start();
		}
	}
	
	/**
	 * get last snap id
	 * 
	 * @return
	 * @throws Exception
	 */
	protected int getLastSnapId() throws Exception {
		return AgensManagerSQLImpl.getSnapshotInfo(userDB, instance);
	}
	
	protected Map<String, Integer> getRangeSnapId() throws Exception {
		int lastSnapID = getLastSnapId();
		
		Map<String, Integer> mapParameter = new HashMap<>();
		if((lastSnapID-8) < 0) mapParameter.put("start_snapid", lastSnapID);
		else mapParameter.put("start_snapid", (lastSnapID-8));
		mapParameter.put("end_snapid", lastSnapID);
		
		return mapParameter;
	}
	
	/**
	 * start runnable
	 * 
	 * @return
	 */
	private Runnable startUIThread() {
		isUIThreadRunning = true;
		final int userSeq = SessionManager.getUserSeq();
		
		Runnable bgRunnable = new Runnable() {
			public void run() {
		    
				while(isUIThreadRunning) {
					if(null != getInstance()) {
					    try {
					    	refreshUI(getUIData());
					    } catch(Exception e) {
					    	logger.error("Job executing", e);
					    }
					}

					// 모니터링 대기.
					try {
						int intTerm = getInterval();
						if(logger.isDebugEnabled()) logger.debug("[agens thread][default term]" + AgensGraphDefine.MONITORING_CYCLE + ":[setting Term]" +  intTerm);
						Thread.sleep(AgensGraphDefine.MONITORING_CYCLE * intTerm);								
					} catch(Exception e){
						logger.error("get monitoring term exception" + e.getMessage());
					}
					
				}	// end while
			}	// end run
			
			/**
			 * get monitoring interval
			 * @return
			 */
			private int getInterval() {
				try {
					return AgensGraphDefine.MONITORING_INTERVAL;
				} catch(Exception e) {
					logger.error("get monitoring interval", e);
					return AgensGraphDefine.MONITORING_INTERVAL;
				}
			}
		};
		
		return bgRunnable;
	}
	
	public abstract List<?> getUIData() throws Exception;
	public abstract void refreshUI(List<?> listData);

	/**
	 * get instance
	 * @return
	 */
	public Instance getInstance() {
		return instance;
	}
	
	/**
	 * get userDB
	 * @return
	 */
	public UserDBDAO getUserDB() {
		return userDB;
	}
	
	@Override
	protected void checkSubclass() {
	}
	
	@Override
	public void dispose() {
		isUIThreadRunning = false;
		super.dispose();
	}

}
