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
package alias.id.siteID;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import alias.AliasID;
import alias.ComponentEditor;

public class SiteIDEditor extends ComponentEditor<AliasID>
{
    private static final long serialVersionUID = 1L;

    private static final String HELP_TEXT = "Site number for P25 systems uses"
			+ " the format RR-SS, where RR = RF Subsystem and SS = Site Number. "
    		+ " (e.g. RFSS 1 Site 3: 01-03).  For other protocols, simply use"
    		+ " a decimal value for the site ID";

    private JTextField mTextField;

	public SiteIDEditor( AliasID aliasID )
	{
		super( aliasID );
		
		initGUI();
		
		setComponent( aliasID );
	}
	
	private void initGUI()
	{
		setLayout( new MigLayout( "fill,wrap 2", "[right][left]", "[][][grow]" ) );

		add( new JLabel( "Site ID:" ) );
		mTextField = new JTextField();
		mTextField.getDocument().addDocumentListener( this );
		add( mTextField, "growx,push" );

		JTextArea helpText = new JTextArea( HELP_TEXT );
		helpText.setLineWrap( true );
		helpText.setBackground( getBackground() );
		add( helpText, "span,grow,push" );
	}
	
	public SiteID getSiteID()
	{
		if( getComponent() instanceof SiteID )
		{
			return (SiteID)getComponent();
		}
		
		return null;
	}

	@Override
	public void setComponent( AliasID aliasID )
	{
		mComponent = aliasID;
		
		SiteID siteID = getSiteID();
		
		if( siteID != null )
		{
			mTextField.setText( siteID.getSite() );
		}
		
		setModified( false );
		
		repaint();
	}

	@Override
	public void save()
	{
		SiteID siteID = getSiteID();
		
		if( siteID != null )
		{
			siteID.setSite( mTextField.getText() );
		}
		
		setModified( false );
	}
}
