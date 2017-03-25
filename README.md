# Printer Plus Communication SDK

## Introduction

	Android library for Printer+ SDK, that provides easy to use different 
	thermal printers via wifi, usb and bluetooth each one of different 
	providers in a simple instructions.
	
	So to use this tool, you don't need to know about ESC/POS and 
	specification for each printer builder, you just need to know how to 
	program in java.

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

	![Download QR]('./img/qr_download.png')