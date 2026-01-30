import { TestBed } from '@angular/core/testing';
import { config } from './karma.conf';

describe('Karma Configuration', () => {
  let karmaConfig: any;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    karmaConfig = {};
    config(karmaConfig);
  });

  it('should set the correct basePath', () => {
    expect(karmaConfig.basePath).toBe('');
  });

  it('should include the correct frameworks', () => {
    expect(karmaConfig.frameworks).toEqual(['jasmine', '@angular-devkit/build-angular']);
  });

  it('should set up the correct plugins', () => {
    expect(karmaConfig.plugins).toContain(jasmine.any(Function));
  });

  it('should configure the coverage reporter', () => {
    expect(karmaConfig.coverageReporter).toEqual({
      dir: jasmine.any(String),
      subdir: '.','
      reporters: [
        { type: 'html' },
        { type: 'text-summary' }
      ]
    });
  });

  it('should set client options for Jasmine', () => {
    expect(karmaConfig.client.jasmine.clearContext).toBe(false);
  });

  it('should set the correct log level', () => {
    expect(karmaConfig.logLevel).toBe(karmaConfig.LOG_INFO);
  });

  it('should configure browsers for development', () => {
    expect(karmaConfig.browsers).toEqual(['ChromeHeadlessNoSandbox']);
  });
});