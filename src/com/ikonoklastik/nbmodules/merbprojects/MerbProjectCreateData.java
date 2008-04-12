/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ikonoklastik.nbmodules.merbprojects;

import com.ikonoklastik.nbmodules.merbprojects.database.MerbDatabaseConfiguration;
import java.io.File;
import org.netbeans.api.ruby.platform.RubyPlatform;

/**
 * Encapsulates data required for creating a new Merb project (empty 
 * or from existing sources).
 * 
 * @author alexbcoles
 */
public class MerbProjectCreateData {

    /**
     * The target platform for the project.
     */
    private final RubyPlatform platform;
    /**
     * The top-level directory for the project.
     */
    private final File dir;
    /**
     * The name for the project.
     */
    private final String name;
    /**
     * Specifies whether to generate base directory structure or not.
     */
    private final boolean create;
    /**
     * The database configuration to use.
     */
    private final MerbDatabaseConfiguration database;
    /**
     * Specifies whether the project might be deployed as a .war file.
     */
    private final boolean deploy;
     /**
     * The instance id of the project's target server.
     */
    private final String serverInstanceId;
    
    /**
     * Constructs a new MerbProjectCreateData instance.
     * @param dir the top-level directory for the project 
     * (need not yet exist but if it does it must be empty).
     * @param name the name for the project.
     * @param create specifies whether to generate base directory structure or not (use
     *        false for existing application)
     * @param database the type of the database to use, e.g. mysql, JavaDB etc.
     * @param jdbc specifies whether JDBC should be used for accessing the database.
     * @param deploy specifies whether the Rake support targets for deploying 
     * the project as a .war file should be added.
     */
    public MerbProjectCreateData(RubyPlatform platform, File dir, String name, boolean create,
            MerbDatabaseConfiguration database, boolean deploy, String serverInstanceId) {
        this.platform = platform;
        this.dir = dir;
        this.name = name;
        this.create = create;
        this.database = database;
        this.deploy = deploy;
        this.serverInstanceId = serverInstanceId;
    }
    
    /**
     * @see #create
     */
    public boolean isCreate() {
        return create;
    }
    
    /**
     * @see #database
     */
    public MerbDatabaseConfiguration getDatabase() {
        return database;
    }
    
    /**
     * @see #deploy
     */
    public boolean isDeploy() {
        return deploy;
    }

    /**
     * @see #dir
     */
    public File getDir() {
        return dir;
    }

    /**
     * @see #name
     */
    public String getName() {
        return name;
    }

    /**
     * @see #serverInstanceId
     */
    public String getServerInstanceId() {
        return serverInstanceId;
    }

    /**
     * @see #platform
     */
    public RubyPlatform getPlatform() {
        return platform;
    }
    
}
