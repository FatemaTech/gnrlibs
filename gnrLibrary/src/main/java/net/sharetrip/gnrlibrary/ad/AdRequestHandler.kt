package net.sharetrip.gnrlibrary.ad

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.sharetrip.gnrads.apiinterface.AdEndpoint
import net.sharetrip.gnrads.model.AdRequest
import net.sharetrip.gnrads.network.BaseResponse
import net.sharetrip.gnrlibrary.network.ServiceGenerator

object AdRequestHandler {
    private val endPoint = ServiceGenerator.createService(AdEndpoint::class.java)
    var adType: String = ""
    var script: String = "document.addEventListener('DOMContentLoaded', function(event) {\n" +
            "            var title = 'Sharetrip.com is offering 40% discount';\n" +
            "            var link = 'www.sharetrip.com';\n" +
            "            var imageSrc = 'https://gnr-dev-ads-storage.s3.ap-southeast-1.amazonaws.com/dev/ads/1018391e-7e88-3267-b8a8-c9ad4747baaa.gif';\n" +
            "            // var imageSrc = 'https://dev-panel.green-red.com/uploads/5fc4eca4173f8.jpg';\n" +
            "            var apiEndPoint = 'http://localhost:3000/ad/interaction';\n" +
            "            var payload = {\n" +
            "                placeId: '6b2207eb-4e1b-386a-8ca3-6e29d4f39fc7',\n" +
            "                propertyId: '0e3d70b9-c527-36c1-9594-242e7b6f68c0',\n" +
            "                cookie: '83LSI-IL388-FFX70-XX158',\n" +
            "                requestId: 'ffd42aaf-e0ac-3146-93b8-23f6321263cd|b1c7b125-a6f0-31a1-80e6-25cd08f20bfc|1018391e-7e88-3267-b8a8-c9ad4747baaa',\n" +
            "                date: '2021-05-24T06:48:49.824Z',\n" +
            "                actionType: ''\n" +
            "            };\n" +
            "            var imageNode = document.createElement('img');\n" +
            "            imageNode.src = imageSrc;\n" +
            "            imageNode.alt = title;\n" +
            "            imageNode.style.width = '100%';\n" +
            "            imageNode.style.width = '100%';\n" +
            "            var linkNode = document.createElement('a');\n" +
            "            linkNode.href = link;\n" +
            "            linkNode.target = '_blank';\n" +
            "            linkNode.appendChild(imageNode);\n" +
            "            var wrapper = document.createElement('div');\n" +
            "            wrapper.className = 'adv-area-wrapper';\n" +
            "            wrapper.appendChild(linkNode);\n" +
            "            var x = document.getElementsByTagName('BODY')[0];\n" +
            "            x.appendChild(wrapper);\n" +
            "            x.onclick = function() {\n" +
            "                var xhr = new XMLHttpRequest();\n" +
            "                xhr.withCredentials = true;\n" +
            "                xhr.open('POST', apiEndPoint);\n" +
            "                xhr.setRequestHeader('Content-Type', 'application/json');\n" +
            "                xhr.setRequestHeader('Access-Control-Allow-Origin', '*');\n" +
            "                payload.actionType = \"click\";\n" +
            "                xhr.send(JSON.stringify(payload));\n" +
            "                xhr.addEventListener('readystatechange', function() {\n" +
            "                    if (this.readyState === 4) {\n" +
            "                        const {\n" +
            "                            response,\n" +
            "                            status\n" +
            "                        } = this;\n" +
            "                        console.log({\n" +
            "                            response,\n" +
            "                            status\n" +
            "                        });\n" +
            "                        if (status === 201) {\n" +
            "                            return response;\n" +
            "                        }\n" +
            "                        return false;\n" +
            "                    }\n" +
            "                });\n" +
            "            };\n" +
            "        });"


    fun initialize() {
       // this.adType = adType.adType
        fetchAds()
    }

    private fun fetchAds() {
        GlobalScope.launch {
            when (val data = endPoint.fetchAds(AdRequest())) {
                is BaseResponse.Success -> {
                    script = data.body.script
                }
                is BaseResponse.ApiError -> {

                }
                is BaseResponse.NetworkError -> {
                }

                is BaseResponse.UnknownError -> {
                }
            }
        }
    }
}