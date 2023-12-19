import { useContext, useEffect, useState } from "react";
import { useNavigate } from 'react-router';
import { useLocation } from 'react-router-dom'
import AuthContext from "../context/AuthContext";
import { parseJwt } from "../utils/Helpers";


export default function OAuth2Redirect() {
    const {userLogin} = useContext(AuthContext);
    const [redirectTo, setRedirectTo] = useState('/login');
    const location = useLocation();

    useEffect(() => {
        const accessToken = extractUrlParameter('token')
        if (accessToken) {
          handleLogin(accessToken)
          const redirect = '/'
          setRedirectTo(redirect)
        }
        let isReloaded = localStorage.getItem("isReloaded");
        if(isReloaded === null) {
            localStorage.setItem("isReloaded", 0);
            window.location.reload();
        }
      }, [])

      const extractUrlParameter = (key) => {
        return new URLSearchParams(location.search).get(key)
      }
    
      const handleLogin = (accessToken) => { // TO-DO: burada jwt yi store etmemiz lazim config icin
        const data = parseJwt(accessToken)
        const user = { data, accessToken }
        userLogin(user)
        localStorage.setItem("user", JSON.stringify(user.data.email));
      };
    
      useNavigate(redirectTo);
    
}