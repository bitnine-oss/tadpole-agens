/*******************************************************************************
 * Copyright Tadplehub (c) 2016 hangum.
 * 
 * Contributors:
 *     hangum - initial API and implementation
 ******************************************************************************/
package com.bitnine.angens.manager.core.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;

/**
 * 서비스 결제 
 * 
 * @author hangum
 *
 */
public class AgensManagerEditorInput implements IEditorInput {
	private UserDBDAO userDB;

	public AgensManagerEditorInput(UserDBDAO userDB) {
		this.userDB = userDB;
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return "Agens Manager";
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "Agens Manager";
	}
	
	/**
	 * get userDB
	 * @return
	 */
	public UserDBDAO getUserDB() {
		return userDB;
	}
}
