/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.break4learning.utilidades;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author break4learning
 */
public class UtilidadesGraficas {
    public String seleccionaDirectorio(){
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);
        return (f.getSelectedFile().toString());
    }
    
    public File seleccionaArchivo(){
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_ONLY); 
        f.showSaveDialog(null);
        return (f.getSelectedFile());
    }
}
