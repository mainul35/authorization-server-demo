// src/Authorized.jsx
import React, { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import axios from "axios";

function Authorized() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();

  useEffect(() => {
    const code = searchParams.get("code");
    if (code) {
      getToken(code);
    } else {
      console.error("Authorization code missing from URL.");
      navigate("/error");
    }
  }, [searchParams, navigate]);

  const getToken = async (code) => {
    const tokenUri = "/api/auth/token";
    // const clientId = "client1";
    // const redirectUri = "http://127.0.0.1:5173/authorized";
    // const codeVerifier = "g4qE6ENmlEHpUxNIv0XrE57LfLjgqG28BRke4XLq6AR"; //REMOVE IF NOT USING PKCE
    // const clientSecret = "secret"; // NEVER STORE SECRETS IN CLIENT-SIDE CODE!

    // const basicAuth = "Basic " + btoa(`${clientId}:${clientSecret}`);

    const params = new URLSearchParams();
    // params.append("grant_type", "authorization_code");
    // params.append("client_id", clientId);
    // params.append("redirect_uri", redirectUri);
    // params.append("scope", "openid");
    // params.append("code_verifier", codeVerifier); //REMOVE IF NOT USING PKCE
    params.append("code", code);

    try {
      const response = await axios.post(tokenUri, params, {
        headers: {
          "Content-Type": "application/json",
          Accept: "*/*",
        },
      });

      console.log("Token received:", response.data);
      localStorage.setItem("access_token", JSON.stringify(response.data));
      navigate("/profile");
    } catch (error) {
      console.error("Error getting token:", error);
      navigate("/error");
    }
  };

  return (
    <div>
      <h1>Authorizing...</h1>
      <p>Please wait while we retrieve your token.</p>
    </div>
  );
}

export default Authorized;
