import { PassedInitialConfig } from 'angular-auth-oidc-client';

const authModuleConfig = { // Remove AuthConfig type
  authority: 'http://localhost:8181/realms/spring-microservices-security-realm',
  redirectUrl: window.location.origin + '/',
  postLogoutRedirectUri: window.location.origin + '/',
  clientId: 'spring-client-credentials-id',
  scope: 'openid profile email',
  responseType: 'code',
  silentRenew: true,
  useRefreshToken: true,
  // Remove client_secret from here - it doesn't belong in auth request params
};

export const authConfig: PassedInitialConfig = {
  config: authModuleConfig,
};