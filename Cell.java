package sudoku;
import javax.swing.JTextPane;
import javax.swing.text.StyleConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import javax.swing.BorderFactory;

//Individual cells to display elements of the sudoku board
public class Cell extends JTextPane {
	public Cell(int dim) {
		super();
		setPreferredSize(new Dimension(dim, dim));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		StyleConstants.setFontSize(attribs, 16);
		setParagraphAttributes(attribs, true);
		setBackground(new Color(200, 200, 200));
	}
}
