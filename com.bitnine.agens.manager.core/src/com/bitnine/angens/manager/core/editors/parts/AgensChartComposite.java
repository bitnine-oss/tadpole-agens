package com.bitnine.angens.manager.core.editors.parts;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Composite;

import com.bitnine.agens.manager.engine.core.dao.domain.Instance;
import com.bitnine.angens.manager.core.editors.parts.statistics.DatabaseStatisticsTableComposite;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;

/**
 * Agens Chart composites
 * 
 * @author hangum
 *
 */
public abstract class AgensChartComposite extends AgensThreadComposite {
	private static final Logger logger = Logger.getLogger(DatabaseStatisticsTableComposite.class);
	
	/**
	 * agens chart composite
	 * 
	 * @param parent
	 * @param userDB
	 * @param instance
	 */
	public AgensChartComposite(Composite parent, UserDBDAO userDB, Instance instance) {
		super(parent, userDB, instance);
	}
	/**
	 * get UI Data
	 * @return
	 * @throws Exception
	 */
	public abstract List<?> getUIData() throws Exception;

}
