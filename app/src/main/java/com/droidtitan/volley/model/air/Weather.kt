package com.droidtitan.volley.model.air

import com.google.gson.annotations.SerializedName

class Weather {

  /** In celcius */
  @SerializedName("temperatura") var temperature: String? = null

  /**
   * Condición del clima, puede ser: no_disponible, despejado_dia, despejado_noche, bruma_dia,
   * bruma_noche, despejado_medio_nublado_dia, despejado_medio_nublado_noche, medio_nublado_dia,
   * medio_nublado_noche, nublado_dia, nublado_noche, lluvia_ligera_dia, lluvia_ligera_noche,
   * lluvia_moderada_dia, lluvia_moderada_noche, lluvia_intensa_dia, lluvia_intensa_noche o
   * desconocido
   */
  @SerializedName("condicion") var condition: String? = null
}
