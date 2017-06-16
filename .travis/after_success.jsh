#!/usr/bin/env node

const { execSync } = require('child_process');

const exec = (command) => execSync(command, { stdio: [0, 1, 2] });


if (process.env.TRAVIS_PULL_REQUEST === 'false') {
    exec('git checkout master');
    exec(`git remote add gh-publish https://${process.env.GIT_TOKEN}@github.com/nicojs/ci-npm-with-lerna.git`)
    exec('git config --global user.email "travis-npa@travis.com"');
    exec('git config --global user.name "Travis NPA"');
    exec('npm run lerna-publish');
} else {
    console.log("We are in a pull request, not releasing");
}