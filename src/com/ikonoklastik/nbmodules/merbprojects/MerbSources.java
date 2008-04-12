/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ikonoklastik.nbmodules.merbprojects;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;

/**
 * Implementation of {@link Sources} interface for {@link RailsProject}.
 * 
 * @author alexbcoles
 */
public class MerbSources implements Sources, PropertyChangeListener, ChangeListener {

    public SourceGroup[] getSourceGroups(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addChangeListener(ChangeListener arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeChangeListener(ChangeListener arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
