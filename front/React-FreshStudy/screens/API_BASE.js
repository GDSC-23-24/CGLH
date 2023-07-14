import axios from "axios"
export const createAxiosObject = () => {
  //AxiosObject
  const { CancelToken } = axios
  const source = CancelToken.source()
  const axiosObject = axios.create({
      baseURL: "http://192.168.0.6:8080/",
      headers: {
          Accept: "application/json",
      },
      cancelToken: source.token,
  })

  const timeout = setTimeout(() => {
      source.cancel(-1)
  }, 10000)
  axiosObject.interceptors.response.use(
      response => {
          clearTimeout(timeout)
          return response
      },
      error => {
          return Promise.reject(error)
      },
  )
  return axiosObject
}