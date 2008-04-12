/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ikonoklastik.nbmodules.merbprojects.database;

import com.ikonoklastik.nbmodules.merbprojects.MerbProject;

/**
 * Handles the database configuration for a Merb project.
 * 
 * @author alexbcoles
 */
public interface MerbDatabaseConfiguration {

    /**
     * Gets the database parameter passed to the rails command, i.e. the name 
     * of the Merb database adapter to be used.
     * 
     * @return the parameter for the Merb generator or <code>null</code> if
     * the Merb generator should not be used for generating database configuration
     * for this.
     */
    String railsGenerationParam();
    
    /**
     * Edits the database config file (database.yml) of the given <code>project</code>
     * as required by this configuration, and in case of JDBC connections, 
     * possibly adds a reference to the required 
     * driver jar files to the properties of the <code>project</code>.
     * 
     * @param projectDir the project whose <code>database.yml</code> is 
     * to be edited.
     */
    void editConfig(MerbProject project);
    
}
