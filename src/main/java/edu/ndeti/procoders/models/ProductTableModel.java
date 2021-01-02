/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ndeti.procoders.models;

import edu.ndeti.procoders.repositories.ProductsRepository;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ahmed
 */
public class ProductTableModel extends AbstractTableModel {
    private final ProductsRepository productsRepository;
    private String[] colNames = {
        "identifier",
        "Image",
        "Name",
        "Description",
        "Price"
    };
    
    private List<Product> rows;
    
    public ProductTableModel() {
        productsRepository = ProductsRepository.getInstance();
        resetData();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            final Product prodcut = rows.get(rowIndex);
            
            final Hashtable<String, Object> productRow = prodcut.toHashTable();
            
            return productRow.get(colNames[columnIndex].toLowerCase());            
        } catch (IllegalAccessException exception) {
            return null;
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public Product getProductAt(int rowIndex) throws IndexOutOfBoundsException {
        return rows.get(rowIndex);
    }

    private void resetData() {
        rows = productsRepository.getProducts();
    }
    
    @Override
    public void fireTableDataChanged() {
        resetData();

        super.fireTableDataChanged(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
