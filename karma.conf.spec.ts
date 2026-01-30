import { TestBed } from '@angular/core/testing';
import { config } from './karma.conf';

describe('Karma Configuration', () => {
  let karmaConfig: any;

  beforeEach(() => {
    karmaConfig = {};
    config(karmaConfig);
  });

  it('should set the base path', () => {
    expect(karmaConfig.basePath).toBe('');
  });

  it('should include the correct frameworks', () => {
    expect(karmaConfig.frameworks).toEqual(['jasmine', '@angular-devkit/build-angular']);
  });

  it('should include the correct plugins', () => {
    expect(karmaConfig.plugins).toEqual([
      jasmine.createSpy('karma-jasmine'),
      jasmine.createSpy('karma-chrome-launcher'),
      jasmine.createSpy('karma-jasmine-html-reporter'),
      jasmine.createSpy('karma-coverage'),
      jasmine.createSpy('@angular-devkit/build-angular/plugins/karma')
    ]);
  });

  it('should configure the coverage reporter correctly', () => {
    expect(karmaConfig.coverageReporter).toEqual({
      dir: jasmine.any(String),
      subdir: '.',
      reporters: [
        { type: 'html' },
        { type: 'text-summary' }
      ]
    });
  });

  it('should set client settings for Jasmine', () => {
    expect(karmaConfig.client.jasmine).toEqual({});
    expect(karmaConfig.client.clearContext).toBe(false);
  });

  it('should set the logging level', () => {
    expect(karmaConfig.logLevel).toBe(karmaConfig.LOG_INFO);
  });

  it('should set autoWatch to true', () => {
    expect(karmaConfig.autoWatch).toBe(true);
  });

  it('should configure the custom Chrome launcher', () => {
    expect(karmaConfig.customLaunchers.ChromeHeadlessNoSandbox).toEqual({
      base: 'ChromeHeadless',
      flags: ['--no-sandbox']
    });
  });
});