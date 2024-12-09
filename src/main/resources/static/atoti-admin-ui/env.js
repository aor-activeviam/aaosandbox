var baseUrl = window.location.href.split('/ui')[0];
var basePath = baseUrl.replace('/:\d{4}/', ':9090')

window.env = {
  contentServer: {
    url: basePath,
    version: "6.1.2"
  },

  atotiServers: {
    "sandboxActivePivot": {
      url: basePath,
      version: "6.1.2",
    },
  },

  jwtServer: {
    url: basePath,
    version: "6.1.2"
  }
};
