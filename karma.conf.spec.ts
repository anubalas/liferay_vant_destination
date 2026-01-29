import { TestBed } from '@angular/core/testing';
import { config } from './karma.conf';

describe('Karma Configuration', () => {
  let karmaConfig: any;

  beforeEach(() => {
    karmaConfig = {};
    config(karmaConfig);
  });

  it('should set the base path correctly', () => {
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

  it('should set client options correctly', () => {
    expect(karmaConfig.client).toEqual({
      jasmine: {},
      clearContext: false
    });
  });

  it('should configure the coverage reporter correctly', () => {
    expect(karmaConfig.coverageReporter).toEqual({
      dir: require('path').join(__dirname, './coverage/sticky-notes'),
      subdir: '.',
      reporters: [
        { type: 'html' },
        { type: 'text-summary' }
      ]
    });
  });

  it('should set the reporters correctly', () => {
    expect(karmaConfig.reporters).toEqual(['progress', 'kjhtml']);
  });

  it('should set the port correctly', () => {
    expect(karmaConfig.port).toBe(9876);
  });

  it('should enable colors in the output', () => {
    expect(karmaConfig.colors).toBe(true);
  });

  it('should set the log level correctly', () => {
    expect(karmaConfig.logLevel).toBe(karmaConfig.LOG_INFO);
  });

  it('should enable autoWatch', () => {
    expect(karmaConfig.autoWatch).toBe(true);
  });

  it('should set singleRun to false', () => {
    expect(karmaConfig.singleRun).toBe(false);
  });

  it('should define custom Chrome launcher', () => {
    expect(karmaConfig.customLaunchers).toEqual({
      ChromeHeadlessNoSandbox: {
        base: 'ChromeHeadless',
        flags: ['--no-sandbox']
      }
    });
  });

  it('should handle Jasmine configuration options correctly', () => {
    karmaConfig.client.jasmine = { random: false, seed: 4321 };
    expect(karmaConfig.client.jasmine.random).toBe(false);
    expect(karmaConfig.client.jasmine.seed).toBe(4321);
  });
});
