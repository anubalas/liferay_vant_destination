import { TestBed } from '@angular/core/testing';
import { config } from 'karma';

describe('Karma Configuration', () => {
  let karmaConfig: any;

  beforeEach(() => {
    karmaConfig = {};
    config.set = jasmine.createSpy('set').and.callFake((cfg) => {
      Object.assign(karmaConfig, cfg);
    });
  });

  it('should configure the base path', () => {
    require('./karma.conf')(config);
    expect(karmaConfig.basePath).toBe('');
  });

  it('should include the correct frameworks', () => {
    require('./karma.conf')(config);
    expect(karmaConfig.frameworks).toEqual(['jasmine', '@angular-devkit/build-angular']);
  });

  it('should include the correct plugins', () => {
    require('./karma.conf')(config);
    expect(karmaConfig.plugins).toContain(jasmine.any(Function));
  });

  it('should set client options', () => {
    require('./karma.conf')(config);
    expect(karmaConfig.client.clearContext).toBe(false);
  });

  it('should configure the coverage reporter', () => {
    require('./karma.conf')(config);
    expect(karmaConfig.coverageReporter.dir).toContain('coverage/sticky-notes');
    expect(karmaConfig.coverageReporter.reporters).toEqual([
      { type: 'html' },
      { type: 'text-summary' }
    ]);
  });

  it('should set the logging level', () => {
    require('./karma.conf')(config);
    expect(karmaConfig.logLevel).toBe(config.LOG_INFO);
  });

  it('should set autoWatch to true', () => {
    require('./karma.conf')(config);
    expect(karmaConfig.autoWatch).toBe(true);
  });

  it('should define custom launcher for Chrome', () => {
    require('./karma.conf')(config);
    expect(karmaConfig.customLaunchers.ChromeHeadlessNoSandbox).toBeDefined();
  });
});