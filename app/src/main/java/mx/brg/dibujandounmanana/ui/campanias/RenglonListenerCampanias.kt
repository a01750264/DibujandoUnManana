package mx.brg.dibujandounmanana.ui.campanias

/*
Esta interface permite saber en qué Campania se hizo click en cualquiera de los dos botones
(Ver Más ó Donar)
 */

interface RenglonListenerCampanias {
    fun clickEnVerMas(position: Int)
    fun clickEnDonar(position: Int)
}