# Vod Ingest
TDC's automated workflow for content providers to ingest of metadata and actual VOD assets etc.


# Content Providers


Deliverables from the content provider:

* Metadata (TDCVOD.xml files)
* Binaries
	* Video asset
	* Images
* Subtitles

## Delivering the files

So the metadata (TDCVOD), binaries, subtitles etc. are put on our secure FTP server.
You just need to place the content into a specific directory (e.g. name of the content provider). Each piece of content devlivered in a seperate directory, which is either the internal asset id of the provider or the specific title of the asset. e.g. 

	[content provider name]/[your-asset-ID or title]/batman_vod.xml
	[content provider name]/[your-asset-ID or title]/batman.ts
	[content provider name]/[your-asset-ID or title]/batman.smi
	[content provider name]/[your-asset-ID or title]/batman_cover.jpg
	[content provider name]/[your-asset-ID or title]/batman_poster.jpg

So for our file structure we want to have each package in separate directory.
So that the TS, xml, images and smi files are in the same directory.
For series we want the same structure pr. season. 

Naming of the directory can be done either through title or if you have any internal provider specific ID.

You can get the credentials and setup of our FTP server from the TDC technical staff.

## Metadata
All the metadata is expected to be in a specific TDC VOD format (see our XSD file: [TDC_VOD_1_3.xsd](src/main/resources/TDC_VOD_1_3.xsd?raw=true)). 

The files should be delivered in UTF-8 encoding on our files system (isilon) as shown in the above section.

Most of the content in the metadata and the tags herein should be straight forward to use and self explanatory. Some are explicitly exaplined here in the next sections.


### Priority
Because it take time to ingest the video binaries, we support different levels of urgency when sending an asset. 

For urgent videos that needs to be rushed through the system, add this tag:

	<App_Data App="MOD" Name="Priority" Value="rush" />

For normal procedure of priority you do not need to set this attribute.


## Mezzanine (Video Binary)
The specific delivered mezzanine-format should be cleared during an onboarding test-phase. 

In general the filename should follow a consistent naming convention. e.g. XXXX12312321.ts - only *.ts and *.mp4 are acceptable container formats for mezzanine files.

# Testing
We have provided a test suite to test your content. So in order to see if your metadata validates against our XSD, please run this:

	java -jar tdc-ingest-test.jar metadata.xml
	
The tdc-ingest-test.jar file can be downloaded here: [tdc-ingest-test.jar](build/tdc-ingest-test.jar?raw=true)
