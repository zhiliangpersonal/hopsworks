/*
 * Changes to this file committed after and not including commit-id: ccc0d2c5f9a5ac661e60e6eaf138de7889928b8b
 * are released under the following license:
 *
 * This file is part of Hopsworks
 * Copyright (C) 2018, Logical Clocks AB. All rights reserved
 *
 * Hopsworks is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Hopsworks is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.  See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 *
 * Changes to this file committed before and including commit-id: ccc0d2c5f9a5ac661e60e6eaf138de7889928b8b
 * are released under the following license:
 *
 * Copyright (C) 2013 - 2018, Logical Clocks AB and RISE SICS AB. All rights reserved
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS  OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
'use strict';
/*
 * Controller for the file selection dialog.
 */
angular.module('hopsWorksApp')
    .controller('SelectEnvYmlCtrl', ['$uibModalInstance', 'DatasetBrowserService', 'growl', 'projectId', 'regex', 'errorMsg',
        function($uibModalInstance, DatasetBrowserService, growl, projectId, regex, errorMsg) {

            var self = this;

            self.selectedFilePath;
            self.environmentYmlDef = {};
            self.envMode = "";
            self.isDir = false;
            self.selected = -1;
            self.datasetBrowser = new DatasetBrowserService(projectId, "ALL", 114);
            self.datasetBrowser.getAll();
            /**
             * Close the modal dialog.
             * @returns {undefined}
             */
            self.close = function() {
                $uibModalInstance.dismiss('cancel');
            };

            /**
             * Select a file.
             * @param {type} filepath
             * @param {type} isDirectory
             * @returns {undefined}
             */
            self.select = function(filepath, isDirectory) {
                self.selectedFilePath = filepath;
                self.isDir = isDirectory;
            };

            self.confirmSelection = function(pythonCtrl, isDirectory) {

                var singleMachineType = !(pythonCtrl.environmentTypes['CPU'] && pythonCtrl.environmentTypes['GPU']);

                if (singleMachineType && self.environmentYmlDef.allYmlPath === "") {
                    growl.error("Please select a .yml file for your Anaconda environment.", {
                        title: "No .yml selected",
                        ttl: 15000
                    });
                } else if (!singleMachineType && (self.environmentYmlDef.cpuYmlPath === "" || self.environmentYmlDef.gpuYmlPath === "")) {
                    growl.error("Please select a CPU and GPU .yml file for your Anaconda environment.", {
                        title: "Select .yml files",
                        ttl: 15000
                    });
                } else {
                    $uibModalInstance.close(self.environmentYmlDef);
                }
            };

            self.selectEnvYml = function(pythonCtrl, index, file) {
                if (file.attributes.dir) {
                    self.select(file.attributes.path, true);
                    self.datasetBrowser.select(file, index)
                } else {
                    self.select(file.attributes.path, false);
                    if (self.envMode === 'CPU') {
                        self.environmentYmlDef.cpuYmlPath = file.attributes.path;
                        self.envMode = "";
                    } else if (self.envMode === 'GPU') {
                        self.environmentYmlDef.gpuYmlPath = file.attributes.path;
                        self.envMode = "";
                    } else if (self.envMode === 'ALL') {
                        self.environmentYmlDef.allYmlPath = file.attributes.path;
                        self.envMode = "";
                    }
                }
            };

        }
    ]);