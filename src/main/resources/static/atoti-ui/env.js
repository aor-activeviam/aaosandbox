var baseUrl = window.location.href.split('/ui')[0];

window.env = {
  contentServer: {
    url: baseUrl,
    version: "6.1.2"
  },

  atotiServers: {
    "sandboxActivePivot": {
      url: baseUrl,
      version: "6.1.2",
    },
  },

  jwtServer: {
    url: baseUrl,
    version: "6.1.2"
  }
};
