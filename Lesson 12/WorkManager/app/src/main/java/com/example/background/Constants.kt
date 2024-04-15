@file:JvmName("Constants")

package com.example.background

// Constantes de notificaciones
@JvmField val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
        "Notificaciones detalladas de WorkManager"
const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
        "Muestra notificaciones cada vez que comienza el trabajo"
@JvmField val NOTIFICATION_TITLE: CharSequence = "Inicio de solicitud de trabajo"
const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
const val NOTIFICATION_ID = 1

// Imagen que se manipula del trabajo
const val IMAGE_MANIPULATION_WORK_NAME = "image_manipulation_work"

// Llaves
const val OUTPUT_PATH = "blur_filter_outputs"
const val KEY_IMAGE_URI = "KEY_IMAGE_URI"
const val TAG_OUTPUT = "OUTPUT"

// Tiempo de respuesta (en milisegundos)
const val DELAY_TIME_MILLIS: Long = 3000
