import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import windicss from 'vite-plugin-windicss';
export default defineConfig({
  server: {

  watch:{
    usePolling:true,
  },
    host: true,
    // strictPort:true,
    port:5173,
    // cors: {
    //   origin: false
    // },
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
      '/ws': {
        target: 'ws://localhost:8080',
        changeOrigin: true,
        ws: true,
      },
      '/socket.io': {
        target: 'ws://localhost:8080',
        changeOrigin: true,
        ws: true,
      },
      '/get-location': {
        target: 'https://ipapi.co',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/get-location/, '/json'),
      },
    },
  },
  plugins: [
    react(),
    windicss(),

  ],

})


