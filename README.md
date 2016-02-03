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
You just need to place the content into a specific directory (e.g. name of the content provider). Each piece of content devlivered in a seperate directory, which is either the internal asset id of the provider or the specific title of the asset. e.g. 

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
All the metadata is expected to be in the standard of Cablelabs 1.1 (see our XSD file: [providerADI.xsd](src/main/resources/providerADI.xsd?raw=true)). 

The files should be delivered in UTF-8 encoding on our files system (isilon) as shown in the above section.

Most of the content in the metadata and the tags herein should be straight forward to use and self explanatory. Some are explicitly exaplined here in the next sections.


### Priority
Because it take time to ingest the video binaries, we support different levels of urgency when sending an asset. 

For urgent videos that needs to be rushed through the system, add this tag:

	<App_Data App="MOD" Name="Priority" Value="rush" />

For normal procedure of priority you do not need to set this attribute.

### Not for Public Viewing (Skipping specific medias for speicific platforms)

So if a media does not have specific rights for a platform we can tag these items to be skipped.
To â€œtagâ€� an asset that is not deployable for our OTT platforms, you should put in:

	<App_Data App=â€œMODâ€� Name=â€œNot_Public_For_Platformsâ€� Value=â€œOTTâ€�/>

in the package tag (Asset_Class=â€œPackageâ€�)

### Multiple Languages

Just add another *metadata* tag for each language.
Example:

	<Asset>
   		<Metadata>   
    		<AMS Asset_Name="" Asset_ID="AAIQB" Asset_Class="Title" Provider="XXXX" Provider_ID="XXXX" Product="" Version_Minor="0" Version_Major="1" Creation_Date="2014-10-23" Verb="" />
		   <App_Data App="MOD" Name="Language" Value="da" />     
		   <App_Data App="MOD" Name="Type" Value="somedanishtitle" />
		   <App_Data App="MOD" Name="Title_Sort_Name" Value="" />
	   </Metadata>
	   <Metadata>      
			<AMS Asset_Name="" Asset_ID="AAIQB" Asset_Class="Title" Provider="XXXX" Provider_ID="XXXX" Product="" Version_Minor="0" Version_Major="1" Creation_Date="2014-10-23" Verb="" /> 
		   <App_Data App="MOD" Name="Language" Value="sv" />
     		<App_Data App="MOD" Name="Type" Value="someswedishtitle" />
			<App_Data App="MOD" Name="Title_Sort_Name" Value="" />
		</Metadata>
	</Asset>


### License 
The license will put the VOD on a given platform or take it away from our platforms if the license window have run out. Example:

			<App_Data App="MOD" Name="Licensing_Window_Start" Value="2015-09-01T00:00:00" />
			<App_Data App="MOD" Name="Licensing_Window_End" Value="2016-08-31T23:59:59" />
				


## Video Binary
The specific delivered format should be know as well as the ending of the filename. E.g. XXXX12312321.ts or other. *.ts are accepted for now.

The link between the ADI and the video binary can be done through naming of the video file or through linking it within the adi.xml it self, like so:

	<Asset>
      <Metadata>
        <AMS Asset_Name="Wire, The, Season 3: 001, Time After Time" Asset_ID="AADYE" Asset_Class="Movie" Provider="XXXX" Provider_ID="XXXX" Product="" Version_Minor="0" Version_Major="1" Description="Wire, The, Season 3: 001, Time After Time" Creation_Date="2015-07-02" Verb="" />
        <App_Data App="MOD" Name="Type" Value="Movie" />
        <App_Data App="MOD" Name="Audio_Type" Value="stereo" />
        <App_Data App="MOD" Name="HD_Content" Value="Y" />
        <App_Data App="MOD" Name="Content_Filesize" Value="0" />
        <App_Data App="MOD" Name="Content_Checksum" Value="" />
      </Metadata>
      <Content />
    </Asset>

## Image Binary

*.jpg are accepted

We want as high scale image as possible. Possible values for images COVER, SAMART
Images are referenced through the ADI file, so that it is possible to link from the ADI file to the images.

COVER is primary. And we would of course also like to get the poster and horizontal images (ARTWORK)

In the ADI you can also describe what format the images are as well (COVER, SAMARTWORK etc.). 

We expect at least one image (cover) per asset in the highest resolution.

	<Asset>
		<Metadata>
			<AMS Provider="XXXX" Product="MOD" Asset_Name="package_000342227" Version_Major="1" Version_Minor="0" Description="COVER asset for HaltandCatchFire0208" Creation_Date="2015-08-11" Provider_ID="XXXX" Asset_ID="XXXX0000000000342227" Asset_Class="COVER"Verb=""/>
			<App_Data App="MOD" Name="Type" Value="cover"/>
			<App_Data App="MOD" Name="Content_FileSize" Value=""/>
			<App_Data App="MOD" Name="Content_CheckSum" Value=""/>
		</Metadata>
		<Content Value="XXXX0000000000342227_360x576_se.jpg"/>
	</Asset>


## Subtitles 

All subtitles as *.srt or *.smi files are accepted. The link between the ADI metadata file can be either through the naming of the subtitle file or an explicit asset class (Asset_Class="Subtitle").

# Full Examples

## Video



A full example of a simple movie can be found here: [XXXX0000000000390144](src/main/resources/XXXX0000000000390144/?raw=true)

## Series
Series contains extra metadata such as season id (which chains the metadata together to a season), season name, summary, episode id etc. 

			<App_Data App="MOD" Name="Season_ID" Value="1" />
			<App_Data App="MOD" Name="Season_Name" Value="The Kennedys" />
			<App_Data App="MOD" Name="Series_Summary"
				Value="Her kommer vi tÃ¦t pÃ¥ Kennedy-familien og dens begivenhedsrige historie. Alt fra livet i magtens centrum til privatlivet med de mange sidespring og personlige tragedier skildres." />
			<App_Data App="MOD" Name="Series_Name" Value=" The Kennedys" />
			<App_Data App="MOD" Name="Episode_ID" Value="1" />
			<App_Data App="MOD" Name="Episode_Name" Value="Joe's Revenge" />

A full example of a series VODs can be found here: 

* [XXXX0000000000390110](src/main/resources/XXXX0000000000390110/?raw=true)
* [XXXX0000000000390109](src/main/resources/XXXX0000000000390109/?raw=true)

# Testing
We have provided a test suite to test your content. So in order to see if your metadata validates against our XSD, please run this:

	java -jar tdc-ingest-test.jar metadata.xml
	
The tdc-ingest-test.jar file can be downloaded here: [tdc-ingest-test.jar](build/tdc-ingest-test.jar?raw=true)