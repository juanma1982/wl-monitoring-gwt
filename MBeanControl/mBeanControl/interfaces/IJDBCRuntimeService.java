package mBeanControl.interfaces;

import java.util.ArrayList;

import mBeanControl.exceptions.ObjectNotFoundException;

public interface IJDBCRuntimeService {

	public ArrayList<IJDBCRuntime> getIJDBCRuntimes() throws ObjectNotFoundException;
}
