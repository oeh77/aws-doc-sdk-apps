//snippet-sourcedescription:[DeleteObjects.kt demonstrates how to delete an object from an Amazon Simple Storage Service (Amazon S3) bucket.]
//snippet-keyword:[AWS SDK for Kotlin]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon S3]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/05/2021]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.kotlin.s3

// snippet-start:[s3.kotlin.delete_objects.import]
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.ObjectIdentifier
import aws.sdk.kotlin.services.s3.model.Delete
import aws.sdk.kotlin.services.s3.model.DeleteObjectsRequest
import kotlin.system.exitProcess
// snippet-end:[s3.kotlin.delete_objects.import]

/**
To run this Kotlin code example, ensure that you have setup your development environment,
including your credentials.

For information, see this documentation topic:
https://docs.aws.amazon.com/sdk-for-kotlin/latest/developer-guide/setup.html
 */
suspend fun main(args: Array<String>) {

    val usage = """
    Usage:
         <bucketName> <objectKey> 

    Where:
        bucketName - the Amazon S3 bucket name that contains the object (for example, bucket1).
        objectKey - the name of the object to delete (for example, book.pdf).
      """

    if (args.size != 2) {
        println(usage)
        exitProcess(0)
    }

    val bucketName = args[0]
    val objectKey = args[1]
    deleteBucketObjects(bucketName, objectKey)
   }

// snippet-start:[s3.kotlin.delete_objects.main]
  suspend fun deleteBucketObjects(bucketName: String, objectName: String) {

        val objectId = ObjectIdentifier{
            key = objectName
        }

        val delOb = Delete{
            objects = listOf(objectId)
        }

        val request = DeleteObjectsRequest {
            bucket = bucketName
            delete= delOb
        }

        S3Client { region = "us-east-1" }.use { s3 ->
                 s3.deleteObjects(request)
                 println("$objectName was deleted from $bucketName")
        }
   }
// snippet-end:[s3.kotlin.delete_objects.main]