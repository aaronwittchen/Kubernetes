import { PassedInitialConfig } from 'angular-auth-oidc-client';

export const authConfig: PassedInitialConfig = {
  config: {
    authority:
      'http://localhost:8181/realms/spring-microservices-security-realm/protocol/openid-connect/auth',
    redirectUrl: window.location.origin,
    postLogoutRedirectUri: window.location.origin,
    clientId: 'spring-client-credentials-id',
    scope: 'openid profile offline_access',
    responseType: 'code',
    silentRenew: true,
    useRefreshToken: true,
    renewTimeBeforeTokenExpiresInSeconds: 30,
  },
};
