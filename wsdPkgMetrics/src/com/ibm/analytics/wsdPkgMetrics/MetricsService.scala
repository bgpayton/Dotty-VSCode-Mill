/* Provides metrics services for WSD Package Metrics. */
package com.ibm.analytics.wsdPkgMetrics

import ammonite.ops.Path
import ImageService.ImageID
import StorageService._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

object MetricsService {
  case class ImageMetrics( platform: Platform, id: ImageID, size: Integer, fileCount: Integer, dupCount: Integer, installTime: Integer )

  /**
   * Gets metrics for the given platform and image id, if the metrics exist.  Returns an Option
   * since they might not :-)
   */
  def metricsForImage(platform: Platform, id: ImageID): Option[ImageMetrics] = {
    Some( ImageMetrics(platform, id, 0, 0, 0, 0) )
  }

  /**
   * Gets whether or not we have already gathered metrics for the given platform and image ID.
   */
  def hasMetricsForImage(platform: Platform, id: ImageID) = {
    false
  }

  /**
   * Generates metrics for the given platform and image id, using the image at the given path.
   */
  def generateMetricsForImage(platform: Platform, id: ImageID, path: Path): Future[ ImageMetrics ] = Future {
    ImageMetrics(platform, id, 0, 0, 0, 0)
  }
}