/*******************************************************************************
 *     SDR Trunk 
 *     Copyright (C) 2014-2016 Dennis Sheirer
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>
 ******************************************************************************/
package alias.id.esn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import alias.AliasID;
import alias.ComponentEditor;

public class ESNEditor extends ComponentEditor<AliasID>
{
    private static final long serialVersionUID = 1L;

    private static final String HELP_TEXT = "An Electronic Serial Number (ESN)"
    		+ " is a unique radio hardware identifier normally composed of"
    		+ " hexadecimal (0-9,A-F) characters.  Use an asterisk (*) to wildcard" 
    		+ " individual digits (e.g. ABCD123* or AB**1**4)";

    private JTextField mTextField;

	public ESNEditor( AliasID aliasID )
	{
		super( aliasID );
		
		initGUI();
		
		setComponent( aliasID );
	}
	
	private void initGUI()
	{
		setLayout( new MigLayout( "fill,wrap 2", "[right][left]", "[][][grow]" ) );

		add( new JLabel( "ESN:" ) );
		mTextField = new JTextField();
		mTextField.getDocument().addDocumentListener( this );
		add( mTextField, "growx,push" );

		JTextArea helpText = new JTextArea( HELP_TEXT );
		helpText.setLineWrap( true );
		helpText.setBackground( getBackground() );
		add( helpText, "span,grow,push" );
	}
	
	public Esn getEsn()
	{
		if( getComponent() instanceof Esn )
		{
			return (Esn)getComponent();
		}
		
		return null;
	}

	@Override
	public void setComponent( AliasID aliasID )
	{
		mComponent = aliasID;
		
		Esn esn = getEsn();
		
		if( esn != null )
		{
			mTextField.setText( esn.getEsn() );
		}
		
		setModified( false );
		
		repaint();
	}

	@Override
	public void save()
	{
		Esn esn = getEsn();
		
		if( esn != null )
		{
			esn.setEsn( mTextField.getText() );
		}
		
		setModified( false );
	}
}
