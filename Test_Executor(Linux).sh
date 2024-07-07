#! /usr/bin/bash
mvn clean test -Dcucumber.options="--tags @sogeti_automation" allure:serve

