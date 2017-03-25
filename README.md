# Printer Plus Communication SDK

## Introduction

	Android library for Printer+ SDK, that provides easy to use different 
	thermal printers via wifi, usb and bluetooth each one of different 
	providers in a simple instructions.
	
	So to use this tool, you don't need to know about ESC/POS and 
	specification for each printer builder, you just need to know how to 
	program in java.

### Example of use

	This is a POS+ receipt implementation with this SDK.

![Download Link](/img/receipt.png)	

## Download

	Download the latest version from jcenter via Gradle:

		compile 'es.rcti:printcom:1.1.0'

## License

	Copyright 2017 RCTIES.

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

   	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
	
## ProGuard

	If you are using ProGuard you might need to add the following option:

		-keep class es.rcti.printerplus.printcom.** { *; }

## Installation steps & how to use

### Step 1: Download Printer+ on your testing device

	You can get it from google play: 

		https://play.google.com/store/apps/details?id=es.rcti.printerplus

![Download Link](/img/qr_download.png)

### Step 2: Download Module to your project

	On your build.gradle add this line (autodownload).

		compile 'es.rcti:printcom:1.1.0'

### Step 3: Let's use the library

	On your MainActivity.java or *.java where you want to place it.
	Write this lines.

		//Note: This lines could be inside of onclick implementation of a button
		//to be executed on demand

		//New instance
		StructReport msr = new StructReport();

        //Some previous configuration
        msr.addItemAlignment( StructReport.ALIGNMENT_CENTER );
        msr.addTextBold(false);
        msr.addTextUnderlined(false);
        msr.addItemSizeFont( StructReport.SIZE_FONT_3 );

        //Print some text
        msr.addText("Hello World!");

        //Instruction to cut paper
        msr.addCut();

        //Instruction to send to Printer+ to be printed.
        PrintTool.sendOrder(MainActivity.this, msr);

## Specifications
	
	Printer+ works on devices with Android API 15 or higher.

	This SDK currently serves for the following:

**Print Text**: 	Unicode characters, use your thermal printer without any limitation 
of code pages to print any character on any language.

**Print Images**: You can print images of your device, we recommend you to use images 
with max-width 400 pixels to work on most printers.

**Print Barcodes**: You can select and print different type of barcode to print, but 
on some models we have place one by default to work fine.

**Print QR**: NOT WORKING YET. We will add this feature in a few days.

**Brands and models**:

	Epson: WiFi, USB and Bluetooth. Most models. Tested on TM-T88IV.
	Bixolon: WiFi, USB and Bluetooth. Most models. Tested on SRP-350II Plus
	Zjiang: Bluetooth. Most models. Tested on 5802LD, 8001LD and HS-589TAI

## Advanced usage

	//This examples requires this imports
		import es.rcti.printerplus.printcom.models.PrintTool;
		import es.rcti.printerplus.printcom.models.StructReport;

	You need an instance of StructReport

		//java code
		StructReport msr = new StructReport();
	
### How to PRINT TEXT

		//To align the text you can use one of this three
		//ALIGNMENT_CENTER, ALIGNMENT_LEFT or ALIGNMENT_RIGHT
		msr.addItemAlignment( StructReport.ALIGNMENT_CENTER ); //Example with center alignment

		//To enable or disable bolding use
		msr.addTextBold(false);

		//To enable or disable underline use
		msr.addTextUnderlined(false);

		//To enable or disable reverse mode use
		msr.addTextReverseMode(false);

		//To set font size, ther are 8 sizes, this goes since SIZE_FONT_1 to SIZE_FONT_8
		msr.addItemSizeFont( StructReport.SIZE_FONT_1 );

		//To print text with the previous configuration use
		msr.addText("Hello");

		//To send cut order use this.
		msr.addCut();

		//To send this instruction to Printer+ use
		PrintTool.sendOrder(MainActivity.this, msr);



**Another example of print text with unicode wrote on code**

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StructReport msr = new StructReport();

                //SOME PREVIOUS CONFIGURATION
                msr.addItemAlignment( StructReport.ALIGNMENT_CENTER );
                msr.addTextBold(false);
                msr.addTextUnderlined(false);
                msr.addItemSizeFont( StructReport.SIZE_FONT_3 );

                //TEXT PRINT
                msr.addText("Good Job! "+getEmojiByUnicode(0x1f44D));

                msr.addCut();

                PrintTool.sendOrder(MainActivity.this, msr);
            }
        });
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }		

**Note:** to print unicode from a EditText, you don't have to do anything.

	//Example getting content from a EditText with unicode characters
	//to write emojis or any character
	msr.addText(myEditTextVar.getText().toString());


### How to PRINT BARCODES

	Print Barcodes is so easy. You have the following features to setting up your barcode
	-HRI: below, above, both and none
	-ALIGNMENT: left, center and right.
	-TYPE: code93, code128, ITF, EAN13, ..., etc.
	-HEIGHT and WIDTH

	Example of barcode with HRI below
		
		//By default each brand of printer are set to a barcode which is working fine, by
		//this reason if you want is not necessary to set a type.

		msr.addItemAlignment( StructReport.ALIGNMENT_CENTER );
		msr.addBarcodeHRI( StructReport.BARCODE_HRI_BELOW );
		msr.addBarcodeData( "1234567890128" );
		msr.addCut();
		PrintTool.sendOrder(MainActivity.this, msr);

### How to PRINT IMAGES

	Print images is easy. You have the following features to setting up your images
	-ALIGNMENT: left, center and right.
	-Path or Bitmap content

	There are two ways to print images:

	-Full path to file:

		msr.addItemAlignment( StructReport.ALIGNMENT_CENTER );
		msr.addImagePath("your/path/to/file/*.png"); // PNGs works on most cases
		msr.addCut();
		PrintTool.sendOrder(MainActivity.this, msr);

	-Bitmap:

		msr.addItemAlignment( StructReport.ALIGNMENT_CENTER );
		msr.addImageBitmap( myBitmapVar );
		msr.addCut();
		PrintTool.sendOrder(MainActivity.this, msr);


## Contact us

Please contact us for any suggestion.

	email: soporte@rcti.es

## Visit us

Find us at: 

	https://www.rcti.es









