# Vod Ingest
TDC's automated workflow for content providers to ingest of metadata and actual VOD assets etc.


# Content Providers


Deliverables from the content provider:

* Metadata (ADI.xml files)
* Binaries
	* Video asset
	* Images
* Subtitles

## Delivering the files

So the metadata (ADI), binaries, subtitles etc. are put on our secure FTP server.
You just need to place the content into a specific directory (e.g. name of the content provider). Each piece of content devlivered in a seperate directory. e.g. 

	[content provider name]/[your-asset-ID or title]/batman_adi.xml
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
All the metadata is expected to be in the standard of Cablelabs 1.1 (see our XSD file: [providerADI.xsd](providerADI.xsd)). 

The files should be delivered in UTF-8 encoding on our files system (isilon).


### Priority
Because it take time to ingest the video binaries, we support different levels of urgency when sending an asset. 

For urgent videos that needs to be rushed through the system, add this tag:

	<App_Data App="MOD" Name="Priority" Value="rush" />

For normal procedure of priority you do not need to set this attribute.

### Skipping specific medias for speicific platforms

So if a media does not have specific rights for a platform we can tag these items to be skipped.
To “tag” an asset that is not deployable for our OTT platforms, you should put in:

	<App_Data App=“MOD” Name=“Not_Public_For_Platforms” Value=“OTT”/>

in the package tag (Asset_Class=“Package”)

### Multiple Languages

Just add another *metadata* tag for each language.
Example:

	<Asset>
   		<Metadata>   
    		<AMS Asset_Name="" Asset_ID="AAIQB" Asset_Class="Title" Provider="CMORE" Provider_ID="CMORE" Product="" Version_Minor="0" Version_Major="1" Creation_Date="2014-10-23" Verb="" />
		   <App_Data App="MOD" Name="Language" Value="da" />     
		   <App_Data App="MOD" Name="Type" Value="somedanishtitle" />
		   <App_Data App="MOD" Name="Title_Sort_Name" Value="" />
	   <Metadata>      
			<AMS Asset_Name="" Asset_ID="AAIQB" Asset_Class="Title" Provider="CMORE" Provider_ID="CMORE" Product="" Version_Minor="0" Version_Major="1" Creation_Date="2014-10-23" Verb="" /> 
		   <App_Data App="MOD" Name="Language" Value="sv" />
     		<App_Data App="MOD" Name="Type" Value="someswedishtitle" />
			<App_Data App="MOD" Name="Title_Sort_Name" Value="" />
	</Asset>



## Video Binary
The specific delivered format should be know as well as the ending of the filename. E.g. CM_RE12312321.ts or other. *.ts are accepted for now.

## Image Binary

*.jpg are accepted

We want as high scale image as possible. Possible values for images COVER, SAMART
Images are referenced through the ADI file, so that it is possible to link from the ADI file to the images.

COVER is primary. And we would of course also like to get the poster and horizontal images (ARTWORK)

In the ADI you can also describe what format the images are as well (COVER, SAMARTWORK etc.). 

We expect at least one image (cover) per asset in the highest resolution.

	<Asset>
		<Metadata>
			<AMS Provider="CMORE" Product="MOD" Asset_Name="package_000342227" Version_Major="1" Version_Minor="0" Description="COVER asset for HaltandCatchFire0208" Creation_Date="2015-08-11" Provider_ID="CMORE" Asset_ID="CMRE0000000000342227" Asset_Class="COVER"Verb=""/>
			<App_Data App="MOD" Name="Type" Value="cover"/>
			<App_Data App="MOD" Name="Content_FileSize" Value=""/>
			<App_Data App="MOD" Name="Content_CheckSum" Value=""/>
		</Metadata>
		<Content Value="CMRE0000000000342227_360x576_se.jpg"/>
	</Asset>


## Subtitles 

*.srt or *.smi files are accepted.

# Full Examples

## Video

## Series

# Testing
We have provided a test suite to test your content. So in order to see if your metadata validates against our XSD, please run this:

	java -jar tdc-ingest-test.jar metadata.xml