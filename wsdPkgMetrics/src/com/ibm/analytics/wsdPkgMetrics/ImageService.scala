/* Provides image management services (download, delete) for WSD Package Metrics. */
package com.ibm.analytics.wsdPkgMetrics

import ammonite.ops._
import ammonite.ops.ImplicitWd._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

/**
 * Provides services for discovering and downloading WSD images.
 */
object ImageService {
  case class ImageID( id: String ) {
    override def toString: String = id
  }

  val ImageURLbase = "foo"
  val FTP_auth = "user:passw"

  /**
   * Gets the URL for the directory containing images for the given platform.
   */
  def imageDirURL(platform: Platform) = {
    ImageURLbase + platformDir(platform)
  }

  /**
   * Gets the image filename to use for the given platform and image ID.
   */
  def imageFilename(platform: Platform, id: ImageID) = {
    val imageFilename = platform match {
      case Mac => "Foo-mac.zip"
      case Win => "Bar-win.exe"
    }
    imageFilename
  }

  /**
   * Gets the URL needed to retrieve an image of the given platform with the given image ID.
   */
  def imageURL(platform: Platform, imageID: ImageID) = {
    imageDirURL(platform) + imageID + "/" + imageFilename(platform, imageID)
  }

  /**
   * Gets the most recent image ID for the given platform.
   */
  def latestImageID(platform: Platform) = {
    println("Image Dir URL is " + imageDirURL(platform))
    val cmdResult = %%("curl", "-u", FTP_auth, "--noproxy", "*", imageDirURL( platform ))

    val cmdResultLines = cmdResult.out.lines
    //cmdResultLines.foreach( println(_) )
    val imageIDStr = cmdResultLines.last.split("\\s+").last

    ImageID(imageIDStr)
  }

  /**
   * Downloads the image for the given platform and with the given ID and places it in the given
   * destination directory.
   */  
  def downloadImage(platform: Platform, id: ImageID, path: Path): Future[CommandResult] = Future {
    println(s"Starting download of ${platform} image ID ${id} to path ${path}...")
    val cmdResult = %%("curl", "-u", FTP_auth, imageURL(platform, id), "--create-dirs", "-o", path )
    println(s"Download of ${platform} image ID ${id} completed!" )

    cmdResult
  }

  /** 
   * Gets the platform directory name for the given platform type.
   */
  def platformDir( platform: Platform ) = {
    val platformDir = platform match {
      case Mac => "MacOSX/"
      case Win => "Win64/"
    }
    platformDir
  }
}

