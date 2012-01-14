package org.jmate.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class AppendableListActivity  extends Activity {
    
	TableLayout list;
	int rowsSoFar = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button addButton = (Button) findViewById( R.id.add );
        // Every time the "+" button is clicked,
        // add a new row to the table.
        addButton.setOnClickListener( new OnClickListener() {
			public void onClick(View view) { addButton(); }
		});
        
        list = (TableLayout) findViewById( R.id.list );
        
        // Start with one row.
        addButton();
    }
    
    /***
     * Gets all the information necessary to delete itself from the constructor.
     * Deletes itself when the button is pressed.
     */
    private static class RowRemover implements OnClickListener {
    	private TableLayout list;
    	private TableRow rowToBeRemoved;
    	
    	/***
    	 * @param list	The list that the button belongs to
    	 * @param row	The row that the button belongs to
    	 */
    	public RowRemover( TableLayout list, TableRow row ) {
    		this.list = list;
    		this.rowToBeRemoved = row;
    	}
    	
    	public void onClick( View view ) {
    		list.removeView( rowToBeRemoved );
    	}
    }
    
    public void addButton() {
    	TableRow newRow = new TableRow( list.getContext() );
    	Button actionButton = new Button( newRow.getContext() );
    	actionButton.setText( "Action: " + ++rowsSoFar );
    	Button removeSelfButton = new Button( newRow.getContext() );
    	removeSelfButton.setText( "-" );
    	// pass on all the information necessary for deletion
    	removeSelfButton.setOnClickListener( new RowRemover( list, newRow ));
    	newRow.addView( actionButton );
    	newRow.addView( removeSelfButton );
    	list.addView( newRow );
    }
}