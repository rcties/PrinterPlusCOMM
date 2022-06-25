package es.rcti.demoprinterplus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.rcti.printerplus.printcom.models.PrintTool;
import es.rcti.printerplus.printcom.models.StructReport;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnText, btnTest;
    private EditText etMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTest = (Button) findViewById(R.id.btn_test);
        btnText = (Button) findViewById(R.id.btn_text);
        etMsg   = (EditText) findViewById(R.id.et_msg);

        btnText.setOnClickListener(this);
        btnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if ( v == btnTest ) {
            sendTest();
        } else if (v == btnText) {
            sendText();
        }
    }

    private void sendText() {
        if ( !etMsg.getText().toString().isEmpty() ) {
            PrintTool.sendOrder( this, getTextSR() );
        } else {
            Toast.makeText(this, "Write some text!", Toast.LENGTH_LONG).show();
        }
    }

    private void sendTest() {
        PrintTool.sendOrder( this, getTestSR() );
    }

    /*
    -----------------------------------------------------------------------------
    THIS IS HOW TO CREATE REPORTS ZONE
    -----------------------------------------------------------------------------
     */

    private StructReport getTestSR() {
        StructReport msr = new StructReport();

        //SOME PREVIOUS CONFIGURATION
        msr.addItemAlignment( StructReport.ALIGNMENT_LEFT );
        msr.addTextBold(false);
        msr.addTextUnderlined(false);
        msr.addItemSizeFont( StructReport.SIZE_FONT_1 );

        //TEXT PRINT
        msr.addText("TESTING ...");

        //SOME CHANGES
        msr.addItemSizeFont( StructReport.SIZE_FONT_2 );
        msr.addText("TESTING ...");
        msr.addItemSizeFont( StructReport.SIZE_FONT_3 );
        msr.addText("TESTING ...");

        //OTHER CHANGES
        msr.addItemSizeFont( StructReport.SIZE_FONT_1 );
        msr.addTextReverseMode(true);
        msr.addItemAlignment( StructReport.ALIGNMENT_CENTER );
        msr.addText("TEXT CENTERED");

        msr.addItemAlignment( StructReport.ALIGNMENT_RIGHT );
        msr.addText("TEXT ON RIGHT");

        msr.addTextReverseMode(false);

        msr.addBarcodeHRI( StructReport.BARCODE_HRI_BELOW );
        msr.addBarcodeData( "1234567890128" );

        msr.addItemAlignment( StructReport.ALIGNMENT_CENTER );
        msr.addBarcodeHRI( StructReport.BARCODE_HRI_ABOVE );
        msr.addBarcodeData( "1234567890128" );

        msr.addItemAlignment( StructReport.ALIGNMENT_LEFT );
        msr.addBarcodeHRI( StructReport.BARCODE_HRI_NONE );
        msr.addBarcodeData( "1234567890128" );

        //ADDING FEEDS
        msr.addText("\n\n\n");

        //FINISHING AND CUT
        msr.addCutType( false );
        msr.addCut();

        return msr;
    }

    private StructReport getTextSR() {
        StructReport msr = new StructReport();

        //SOME PREVIOUS CONFIGURATION
        msr.addItemAlignment( StructReport.ALIGNMENT_LEFT );
        msr.addTextBold(false);
        msr.addTextUnderlined(false);
        msr.addItemSizeFont( StructReport.SIZE_FONT_1 );

        //GET AND SEND DATA FROM EDIT TEXT
        msr.addText(etMsg.getText().toString());

        //FINISHING
        msr.addCutType( true );
        msr.addCut();

        return msr;
    }
}