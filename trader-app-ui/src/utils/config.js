export const HostURLsMapping = {
  'localhost': 'https://localhost:8080',
  'esrefarch-demo-trader-ui-reflective-roan.cfapps.io': 'https://esrefarch-demo-trader-app-shiny-oryx.cfapps.io',
  'devoxxtrader.cfapps.io': 'https://esrefarch-demo-trader-app-shiny-oryx.cfapps.io',
};
export const ApiConfig = () => {
  const hostname = window.location.hostname;
  const apiURL = HostURLsMapping[hostname];
  return apiURL || '';
};
