@file:JvmName("WorkerUtils")

 package com.example.background.workers
 
 import android.app.NotificationChannel
 import android.app.NotificationManager
 import android.content.Context
 import android.graphics.Bitmap
 import android.net.Uri
 import android.os.Build
 import android.util.Log
 import androidx.annotation.WorkerThread
 import androidx.core.app.NotificationCompat
 import androidx.core.app.NotificationManagerCompat
 import androidx.renderscript.Allocation
 import androidx.renderscript.Element
 import androidx.renderscript.RenderScript
 import androidx.renderscript.ScriptIntrinsicBlur
 import com.example.background.CHANNEL_ID
 import com.example.background.DELAY_TIME_MILLIS
 import com.example.background.NOTIFICATION_ID
 import com.example.background.NOTIFICATION_TITLE
 import com.example.background.OUTPUT_PATH
 import com.example.background.R
 import com.example.background.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
 import com.example.background.VERBOSE_NOTIFICATION_CHANNEL_NAME
 import java.io.File
 import java.io.FileNotFoundException
 import java.io.FileOutputStream
 import java.io.IOException
 import java.util.UUID
 
 /**
  * Crea una notificación que se muestra como una notificación emergente si es posible.
  *
  * Para este codelab, esto se utiliza para mostrar una notificación para que sepa cuándo diferentes pasos
  * de la cadena de trabajo en segundo plano están comenzando
  *
  * @param message Mensaje mostrado en la notificación
  * @param context Contexto necesario para crear Toast
  */
 private const val TAG = "WorkerUtils"
 fun makeStatusNotification(message: String, context: Context) {
 
     // Crea un canal si es necesario
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         // Crea el NotificationChannel, pero solo en API 26+ porque
         // la clase NotificationChannel es nueva y no está en la biblioteca de compatibilidad
         val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
         val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
         val importance = NotificationManager.IMPORTANCE_HIGH
         val channel = NotificationChannel(CHANNEL_ID, name, importance)
         channel.description = description
 
         // Agrega el canal
         val notificationManager =
                 context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
 
         notificationManager?.createNotificationChannel(channel)
     }
 
     // Crea la notificación
     val builder = NotificationCompat.Builder(context, CHANNEL_ID)
             .setSmallIcon(R.drawable.ic_launcher_foreground)
             .setContentTitle(NOTIFICATION_TITLE)
             .setContentText(message)
             .setPriority(NotificationCompat.PRIORITY_HIGH)
             .setVibrate(LongArray(0))
 
     // Muestra la notificación
     NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
 }
 
 /**
  * Método para dormir durante una cantidad fija de tiempo para emular un trabajo más lento
  */
 fun sleep() {
     try {
         Thread.sleep(DELAY_TIME_MILLIS, 0)
     } catch (e: InterruptedException) {
         Log.e(TAG, e.message.toString())
     }
 
 }
 
 /**
  * Difumina la imagen Bitmap dada
  * @param bitmap Imagen a difuminar
  * @param applicationContext Contexto de la aplicación
  * @return Imagen Bitmap difuminada
  */
 @WorkerThread
 fun blurBitmap(bitmap: Bitmap, applicationContext: Context): Bitmap {
     lateinit var rsContext: RenderScript
     try {
 
         // Crea el bitmap de salida
         val output = Bitmap.createBitmap(
                 bitmap.width, bitmap.height, bitmap.config)
 
         // Difumina la imagen
         rsContext = RenderScript.create(applicationContext, RenderScript.ContextType.DEBUG)
         val inAlloc = Allocation.createFromBitmap(rsContext, bitmap)
         val outAlloc = Allocation.createTyped(rsContext, inAlloc.type)
         val theIntrinsic = ScriptIntrinsicBlur.create(rsContext, Element.U8_4(rsContext))
         theIntrinsic.apply {
             setRadius(10f)
             theIntrinsic.setInput(inAlloc)
             theIntrinsic.forEach(outAlloc)
         }
         outAlloc.copyTo(output)
 
         return output
     } finally {
         rsContext.finish()
     }
 }
 
 /**
  * Escribe el bitmap en un archivo temporal y devuelve el Uri para el archivo
  * @param applicationContext Contexto de la aplicación
  * @param bitmap Bitmap para escribir en el archivo temporal
  * @return Uri para el archivo temporal con el bitmap
  * @throws FileNotFoundException Lanza si no se puede encontrar el archivo de bitmap
  */
 @Throws(FileNotFoundException::class)
 fun writeBitmapToFile(applicationContext: Context, bitmap: Bitmap): Uri {
     val name = String.format("blur-filter-output-%s.png", UUID.randomUUID().toString())
     val outputDir = File(applicationContext.filesDir, OUTPUT_PATH)
     if (!outputDir.exists()) {
         outputDir.mkdirs() // debería tener éxito
     }
     val outputFile = File(outputDir, name)
     var out: FileOutputStream? = null
     try {
         out = FileOutputStream(outputFile)
         bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* ignorado para PNG */, out)
     } finally {
         out?.let {
             try {
                 it.close()
             } catch (ignore: IOException) {
             }
 
         }
     }
     return Uri.fromFile(outputFile)
 }
 