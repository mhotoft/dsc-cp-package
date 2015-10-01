# Vod Ingest
TDC's automated workflow for content providers to ingest of metadata and actual VOD assets etc.


# Content Providers


Deliverables from the content provider:

* Metadata (ADI.xml files)
* Binaries
	* Video asset
	* Images
* Subtitles


## Metadata
All the metadata is expected to be in the standard of Cablelabs 1.1 (see our XSD file: [providerADI.xsd](providerADI.xsd)). 

The files should be delivered in UTF-8 encoding on our files system (isilon).


### Priority
Because it take time to ingest the video binaries, we support different levels of urgency when sending an asset. 

For urgent videos that needs to be rushed through the system, add this tag:

	<App_Data App="MOD" Name="Priority" Value="rush" />

For normal procedure of priority you do not need to set this attribute.

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
The specific delivered format should be know as well as the ending of the filename. E.g. CM_RE12312321.ts or other. 

## Image Binary

We want as high scale image as possible. Possible values for images COVER, SAMART

## Subtitles 


# Full Examples

## Video

## Series