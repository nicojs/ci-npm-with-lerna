#!/usr/bin/env node

const { execSync } = require('child_process');

const exec = (command) => execSync(command, { stdio: [0, 1, 2] });

console.log(`Running on branch ${process.env.TRAVIS_BRANCH}, version ${process.env.TRAVIS_NODE_VERSION}`);

if (process.env.TRAVIS_PULL_REQUEST === 'false'
    && process.env.TRAVIS_BRANCH === 'release'
    && process.env.TRAVIS_NODE_VERSION === '8') {
    exec(`git remote add gh-publish https://${process.env.GIT_TOKEN}@github.com/nicojs/ci-npm-with-lerna.git`)
    exec('git config --global user.email "travis-npa@travis.com"');
    exec('git config --global user.name "Travis NPA"');
    exec('git fetch gh-publish');
    exec('git checkout --track -b master gh-publish/master');
    exec('npm run lerna-publish');
} else {
    console.log('Sowy, no release today');
}