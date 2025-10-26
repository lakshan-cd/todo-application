import { ErrorType } from "@/types/common";
import axios from "axios";
// import { enqueueSnackbar } from "notistack";

const axiosInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL,
  });

interface ErrorResponse {
  title: string;
  status: number;
  detail?: string;
}

const unhandledError: ErrorType = {
  response: {
    data: {
      error: "Something Went Wrong, Please Contact Technical Team.",
      exceptionCode: "500",
      status: "500",
    },
  },
};

// Add a response interceptor
axiosInstance.interceptors.response.use(
  (response) => response,
  async (error) => {
    if (error.response) {
      const { status, data } = error.response;

      let errorResp = { ...unhandledError };

      switch (status) {
        case 400:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Bad Request",
                exceptionCode: "400",
                status: "400",
              },
            },
          };
          break;
        case 401:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Unauthorized",
                exceptionCode: "401",
                status: "401",
              },
            },
          };
          break;
        case 402:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Payment Required",
                exceptionCode: "402",
                status: "402",
              },
            },
          };
          break;
        case 403:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Forbidden",
                exceptionCode: "403",
                status: "403",
              },
            },
          };
          break;
        case 404:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Not Found",
                exceptionCode: "404",
                status: "404",
              },
            },
          };
          break;
        case 405:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Method Not Allowed",
                exceptionCode: "405",
                status: "405",
              },
            },
          };
          break;
        case 408:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Request Timeout",
                exceptionCode: "408",
                status: "408",
              },
            },
          };
          break;
        case 409:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Conflict",
                exceptionCode: "409",
                status: "409",
              },
            },
          };
          break;
        case 429:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Too Many Requests",
                exceptionCode: "429",
                status: "429",
              },
            },
          };
          break;
        case 500:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Internal Server Error",
                exceptionCode: "500",
                status: "500",
              },
            },
          };
          break;
        case 502:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Bad Gateway",
                exceptionCode: "502",
                status: "502",
              },
            },
          };
          break;
        case 503:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Service Unavailable",
                exceptionCode: "503",
                status: "503",
              },
            },
          };
          break;
        case 504:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || "Gateway Timeout",
                exceptionCode: "504",
                status: "504",
              },
            },
          };
          break;
        default:
          errorResp = {
            ...errorResp,
            response: {
              data: {
                error: data.error || data || "An error occurred",
                exceptionCode: "500",
                status: "500",
              },
            },
          };
          break;
      }

      throw errorResp;
    } else {
      // enqueueSnackbar(unhandledError.title, { variant: "error" });
      throw error;
    }
  }
);

export const post = async (url: string, data: any, auth = false) => {
  const options: any = {
    data,
    url,
    method: "post",
    responseType: "json",
  };

  return axiosInstance(options);
};

export const put = async (url: string, data: any) => {
  const options: any = {
    data,
    url,
    method: "put",
    responseType: "json",
  };

  return axiosInstance(options);
};

export const deleteReq = async (url: string, data: any) => {
  const options: any = {
    data,
    url,
    method: "delete",
    responseType: "json",
  };

  return axiosInstance(options);
};

export const patch = async (url: string, data: any) => {
  const options: any = {
    data,
    url,
    method: "patch",
    responseType: "json",
  };

  return axiosInstance(options);
};

export const get = async (url: string, auth = false) => {
  const options: any = {
    url,
    method: "get",
    responseType: "json",
  };

  return axiosInstance(options);
};

export const remove = async (url: string) => {
  const options: any = {
    url,
    method: "delete",
    responseType: "json",
  };

  return axiosInstance(options);
};



export default axiosInstance;
