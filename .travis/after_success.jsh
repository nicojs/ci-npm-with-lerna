#!/bin/node

const { execSync } = require('child_process');

if (process.env.TRAVIS_PULL_REQUEST === 'false') {
    execSync('npm run lerna-publish', { stdio: [0, 1, 2] });
} else {
    console.log("We are in a pull request, not releasing");
}