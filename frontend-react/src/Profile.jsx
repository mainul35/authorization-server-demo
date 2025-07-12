// src/Profile.jsx
import React from "react";

function Profile() {
  const tokenData = localStorage.getItem("access_token");
  const token = tokenData ? JSON.parse(tokenData) : null;

  if (!token) {
    return <div>Please Login First</div>;
  }

  return (
    <div>
      <h1>Profile</h1>
      <p>Welcome to your profile page!</p>
      <pre>{JSON.stringify(token, null, 2)}</pre>
    </div>
  );
}

export default Profile;
