import { browser } from 'protractor';
describe('HomePage', () => {
  it('should navigate to home page', () => {
    browser.get(browser.baseUrl);
    expect(browser.getCurrentUrl()).toContain('/homepage');
  });
  it('should have the correct title', () => {
    browser.get(browser.baseUrl);
    expect(browser.getTitle()).toBe('MovieHub');
  });
}

);