package com.cabinet.util;

import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.io.IOException;

public class ClipboardManager implements ClipboardOwner{

	@Override
	public void lostOwnership(Clipboard aClipboard, Transferable aConten){}
	
	public void setClipboardContent(String content){
		StringSelection stringSelection = new StringSelection(content);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, this);
	}
	
	public static String getClipboardContent(){
		String clipboardData="";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		Transferable content = clipboard.getContents(null);
		
		boolean hasTransferableText = (content != null) && content.isDataFlavorSupported(DataFlavor.stringFlavor);
		
		if(hasTransferableText){
			try {
				clipboardData = (String)content.getTransferData(DataFlavor.stringFlavor);
			} catch(UnsupportedFlavorException | IOException ex){
				JOptionPane.showMessageDialog(null, "Error retriving clipboard data", "Information to user", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		return clipboardData;
	}
}
