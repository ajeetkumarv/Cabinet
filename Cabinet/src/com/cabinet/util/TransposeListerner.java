package com.cabinet.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransposeListerner implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String data = ClipboardManager.getClipboardContent();
		
		new ClipboardManager().setClipboardContent(Transpose.transpose(data, "\t"));
	}

}
