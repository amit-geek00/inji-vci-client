package io.mosip.vciclient.authorizationCodeFlow.interactiveAuthorization.presentationDuringIssuance

import com.google.gson.annotations.SerializedName
import io.mosip.vciclient.authorizationCodeFlow.interactiveAuthorization.response.InteractionResponse

data class PresentationInteractionResponse(
    @SerializedName("status")
    override val status: String,
    @SerializedName("type")
    override val type: String,
    @SerializedName("auth_session")
    override val authSession: String,
    @SerializedName("openid4vp_request")
    val openid4vpRequest: Map<String, Any>
) : InteractionResponse(status, type, authSession) {

     private companion object {
        const val DCP_OPENID4VP_PRESENTATION =
           "urn:openid:dcp:iae:openid4vp_presentation"
    }

    override fun validate() {

     
        if (
            type != "openid4vp_presentation" &&
            type != DCP_OPENID4VP_PRESENTATION
        ) {
            throw IllegalArgumentException(
                "Invalid type: expected 'openid4vp_presentation' or '$DCP_OPENID4VP_PRESENTATION'"
            )
        }

        if (openid4vpRequest.isEmpty()) {
            throw IllegalArgumentException("openid4vpRequest must not be empty")
        }
    }
}