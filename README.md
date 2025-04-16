[google-services.json](https://github.com/user-attachments/files/19772156/google-services.json)# 책 GPT팀 독서 SNS 및 습관 관리 앱 개발 프로젝트

## [감상문 작성 기능] API 키 연결방법

<details><summary>자세히</summary>

### 1. ChatGPT API 연결

1. #### 맨 처음, `C:\MeltingBooks\` 위치에 있는 local.properties에 API 키를 추가 한다.

 ![image](https://github.com/user-attachments/assets/886c04a9-4cbb-4366-a7a9-0ec56d4c7ba8)

<API 키--노출주의!!!>

local.properties 열어서 아래 줄 그대로 맨 마지막 줄에 추가하기 

OPENAI_API_KEY=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




### 2. Google Firebase Console API 연결
1. #### 맨 처음, 아래 service.account.json파일을 `C:\MeltingBooks/app/src/main/assets/` 위치에 추가 한다.

   [service-account.json.json](https://github.com/user-attachments/files/19771882/service-account.json.json)



2. #### google.service.json 파일을 C:\MeltingBooks\app'  위치에 추가한다.

![image](https://github.com/user-attachments/assets/27abe55c-9e54-4618-8e33-b08479579851)

[google-services.json](https://github.com/user-attachments/files/19772167/google-services.json)
{
  "project_info": {
    "project_number": "530155572269",
    "firebase_url": "https://bookgpt-e18a5-default-rtdb.asia-southeast1.firebasedatabase.app",
    "project_id": "bookgpt-e18a5",
    "storage_bucket": "bookgpt-e18a5.firebasestorage.app"
  },
  "client": [
    {
      "client_info": {
        "mobilesdk_app_id": "1:530155572269:android:37976b88f89bdab115fca4",
        "android_client_info": {
          "package_name": "com.example.meltingbooks"
        }
      },
      "oauth_client": [
        {
          "client_id": "530155572269-02v36equ7jl9ffis4v56bfq1vlq3sghg.apps.googleusercontent.com",
          "client_type": 3
        }
      ],
      "api_key": [
        {
          "current_key": "AIzaSyDxAynZdWJ95U91uiSuuHQLE-EOdEtB-Xw"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": [
            {
              "client_id": "530155572269-02v36equ7jl9ffis4v56bfq1vlq3sghg.apps.googleusercontent.com",
              "client_type": 3
            }
          ]
        }
      }
    },
    {
      "client_info": {
        "mobilesdk_app_id": "1:530155572269:android:12b8db38c10f735015fca4",
        "android_client_info": {
          "package_name": "com.example.myapplication"
        }
      },
      "oauth_client": [
        {
          "client_id": "530155572269-02v36equ7jl9ffis4v56bfq1vlq3sghg.apps.googleusercontent.com",
          "client_type": 3
        }
      ],
      "api_key": [
        {
          "current_key": "AIzaSyDxAynZdWJ95U91uiSuuHQLE-EOdEtB-Xw"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": [
            {
              "client_id": "530155572269-02v36equ7jl9ffis4v56bfq1vlq3sghg.apps.googleusercontent.com",
              "client_type": 3
            }
          ]
        }
      }
    }
  ],
  "configuration_version": "1"
}
