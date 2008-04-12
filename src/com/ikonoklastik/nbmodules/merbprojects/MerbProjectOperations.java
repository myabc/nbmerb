/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ikonoklastik.nbmodules.merbprojects;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.CopyOperationImplementation;
import org.netbeans.spi.project.DeleteOperationImplementation;
import org.netbeans.spi.project.MoveOperationImplementation;
import org.openide.filesystems.FileObject;

/**
 *
 * @author alexbcoles
 */
public class MerbProjectOperations implements DeleteOperationImplementation, CopyOperationImplementation, MoveOperationImplementation {

    private MerbProject project;
 
    public MerbProjectOperations(MerbProject project) {
        this.project = project;
    }
    
    public void notifyDeleting() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyDeleted() throws IOException {
        project.getRakeProjectHelper().notifyDeleted();
    }

    public List<FileObject> getMetadataFiles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<FileObject> getDataFiles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyCopying() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyCopied(Project arg0, File arg1, String arg2) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyMoving() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyMoved(Project arg0, File arg1, String arg2) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
