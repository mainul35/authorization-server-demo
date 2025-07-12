CREATE TABLE IF NOT EXISTS oauth2_authorization (
                                                    id VARCHAR(100) PRIMARY KEY,
    registered_client_id VARCHAR(100) NOT NULL,
    principal_name VARCHAR(200) NOT NULL,
    authorization_grant_type VARCHAR(100) NOT NULL,
    authorized_scopes VARCHAR(1000),
    attributes VARCHAR,
    state VARCHAR(500),

    authorization_code_value VARCHAR,
    authorization_code_issued_at TIMESTAMP,
    authorization_code_expires_at TIMESTAMP,
    authorization_code_metadata VARCHAR,

    access_token_value VARCHAR,
    access_token_issued_at TIMESTAMP,
    access_token_expires_at TIMESTAMP,
    access_token_metadata VARCHAR,
    access_token_type VARCHAR(100),
    access_token_scopes VARCHAR(1000),

    oidc_id_token_value VARCHAR,
    oidc_id_token_issued_at TIMESTAMP,
    oidc_id_token_expires_at TIMESTAMP,
    oidc_id_token_metadata VARCHAR,

    refresh_token_value VARCHAR,
    refresh_token_issued_at TIMESTAMP,
    refresh_token_expires_at TIMESTAMP,
    refresh_token_metadata VARCHAR,

    user_code_value VARCHAR,
    user_code_issued_at TIMESTAMP,
    user_code_expires_at TIMESTAMP,
    user_code_metadata VARCHAR,

    device_code_value VARCHAR,
    device_code_issued_at TIMESTAMP,
    device_code_expires_at TIMESTAMP,
    device_code_metadata VARCHAR,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
    );
